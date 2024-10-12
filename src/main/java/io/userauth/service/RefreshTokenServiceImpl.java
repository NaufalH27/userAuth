package io.userauth.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.models.RefreshToken;
import jakarta.transaction.Transactional;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    @Transactional
    public UUID generateToken(UUID userIdIssuer) {
        RefreshToken newRefreshToken =  new RefreshToken();
        newRefreshToken.setToken(UUID.randomUUID());
        newRefreshToken.setUserId(userIdIssuer);
        refreshTokenRepository.addToken(newRefreshToken);
        return newRefreshToken.getToken();
    }

    @Override
    @Transactional
    public void revokeToken(UUID token) {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(token);
        refreshToken.setIsRevoked(true);
    }
}
