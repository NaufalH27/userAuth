package io.userauth.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.userauth.presentation.dto.user.UserDTO;


@Service
public class JWTService{

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(UserDTO user) {
        return Jwts.builder()
                .setIssuer("userauth")
                .setSubject(user.getName())
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); 
        return Keys.hmacShaKeyFor(keyBytes);                
    }


    
}
