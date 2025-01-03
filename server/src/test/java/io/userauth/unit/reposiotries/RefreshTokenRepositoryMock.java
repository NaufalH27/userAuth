package io.userauth.unit.reposiotries;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.models.RefreshToken;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class RefreshTokenRepositoryMock {
    
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    @Test
    void testInsertion() {
        RefreshToken mockRefreshToken = new RefreshToken();
        mockRefreshToken.setToken(UUID.randomUUID());
        mockRefreshToken.setUserId(UUID.randomUUID());
        assertNotNull(refreshTokenRepository.findTokenById(mockRefreshToken.getToken()));
    }


    @Test
    void testFindTokenById_nonexistedToken() {
        UUID mockNonExistedToken = UUID.randomUUID();
        assertNull(refreshTokenRepository.findTokenById(mockNonExistedToken));
    }

}
