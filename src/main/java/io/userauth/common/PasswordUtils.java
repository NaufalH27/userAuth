package io.userauth.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String plainPassword) {
        try {
            return encoder.encode(plainPassword);
        } catch (Exception e) {
            throw new RuntimeException("Error encoding password: " + e.getMessage(), e);
        }
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }

}

