package io.userauth.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.userauth.dto.auth.AuthenticatedUser;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTHelper {

    @Value("${jwt.secret}")
    String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey); 
        return Keys.hmacShaKeyFor(keyBytes);                
    }


    public void sendTokenToClient(AuthenticatedUser user, HttpServletResponse response) {
        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken();
        CookieUtils.sendCookies(response, "AccessToken", accessToken);
        CookieUtils.sendCookies(response, "refreshToken", refreshToken);
    }

    public void refreshToken() {

    }

    private String generateAccessToken(AuthenticatedUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId().toString());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        String subject = user.getUsername();
        return buildAccessToken(claims, subject);
    }

    private String buildAccessToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setIssuer("userauth")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.expirationTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private String generateRefreshToken(){
        return UUID.randomUUID().toString();
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
        Object id = claims.get("id");

        if (id == null || !(id instanceof String)){
            throw new IllegalArgumentException();
        }

        return UUID.fromString((String) claims.get("id"));
    }


    public Collection<? extends GrantedAuthority> getGrantedAuthorities(String token){
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roleList = getRoleList(token);
        roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            });
        return authorities;
    }
   
    private List<String> getRoleList(String token) {
        final Claims claims = this.extractAllClaims(token);
        Object roles = claims.get("role");
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
        SecretKey Key = getSignInKey();
        return Jwts.parserBuilder().setSigningKey(Key).build().parseClaimsJws(token).getBody();
    }

}
