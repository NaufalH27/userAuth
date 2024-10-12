package io.userauth.unit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.service.RefreshTokenService;


@SpringBootTest
public class RefreshTokenServiceMock {

    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Test
    void generateRefreshToken() {
        assertNotNull(refreshTokenService.generateToken(UUID.randomUUID()));
        
    }

    @Test
    void tokenRevokation_success() {
        UUID MockToken = refreshTokenService.generateToken(UUID.randomUUID());
        refreshTokenService.revokeToken(MockToken);
    }


}
