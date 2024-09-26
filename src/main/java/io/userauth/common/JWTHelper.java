package io.userauth.common;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTHelper {

    @Value("${jwt.secret}")
    String secretKey;

    private Claims extractAllClaims(String token) {
        SecretKey Key = Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder().setSigningKey(Key).build().parseClaimsJws(token).getBody();
     
    }

    public String getSubject(String token){
        final Claims claims = this.extractAllClaims(token);
        return claims.getSubject();        
    }

    public List<String> getRole(String token){
        final Claims claims = this.extractAllClaims(token);
        return claims.get("role");        
    }

    public UUID getId(String token){
        final Claims claims = this.extractAllClaims(token);
        return UUID.fromString((String) claims.get("id"));
    }


}
