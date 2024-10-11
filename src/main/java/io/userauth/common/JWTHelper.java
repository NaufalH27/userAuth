package io.userauth.common;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.userauth.constant.JWTClaimName;

@Service
public class JWTHelper {

    @Value("${jwt.secret}")
    String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String buildAccessToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setIssuer(JWTClaimName.ISSUER)
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.expirationTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey); 
        return Keys.hmacShaKeyFor(keyBytes);                
    }

    public String getSubject(String token) {
        final Claims claims = this.extractAllClaims(token);
        Object subject = claims.getSubject();

        if (subject == null || !(subject instanceof String)){
            throw new IllegalArgumentException();
        }
        return (String) subject;     
    }

    public UUID getId(String token) {
        final Claims claims = this.extractAllClaims(token);        
        Object id = claims.get(JWTClaimName.ID);

        if (id == null || !(id instanceof String)){
            throw new IllegalArgumentException();
        }
        return UUID.fromString((String) id);
    }
   
    public List<String> getRoleList(String token) {
        final Claims claims = this.extractAllClaims(token);
        Object roles = claims.get(JWTClaimName.ROLES);

        if(roles != null && roles instanceof List<?>){
            List<?> roleList = (List<?>) roles;
            
            if(roleList.stream().allMatch(role -> role instanceof String)){
                return roleList.stream()
                           .map(role -> (String) role)
                           .collect(Collectors.toList());
            }
        }
        throw new IllegalArgumentException();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

}
