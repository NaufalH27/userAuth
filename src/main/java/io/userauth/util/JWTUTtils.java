package io.userauth.util;

import org.springframework.beans.factory.annotation.Value;

public class JWTUTtils {
    
    @Value("${jwt.secret}")
    String secretKey;

    public static boolean validateToken(String token) {
        return true;
    }
}
