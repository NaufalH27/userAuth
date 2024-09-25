package io.userauth.common;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTHelper {

    @Value("${jwt.secret}")
    String secretKey;

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        SecretKey Key = Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
            .setSigningKey(Key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public String getSubject(String token){
        try{
            final Claims claims = this.extractAllClaims(token);
            return claims.getSubject();        
        }catch(Exception e){
            return null;
        }
    }

    public String getRole(String token){
        try{
            final Claims claims = this.extractAllClaims(token);
            return (String) claims.get("role");        
        }catch(Exception e){
            return null;
        }
    }


}
