package io.userauth.service;

import java.util.UUID;

import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.models.RefreshToken;
import jakarta.transaction.Transactional;

public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    @Transactional
    public String generateToken(UUID userIdIssuer) {
        RefreshToken newRefreshToken =  new RefreshToken();
        newRefreshToken.setToken(UUID.randomUUID());
        newRefreshToken.setUserId(userIdIssuer);
        refreshTokenRepository.addToken(newRefreshToken);
        return newRefreshToken.getToken().toString();
    }

    @Override
    @Transactional
    public void revokeToken(UUID token) {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(token);
        refreshToken.setIsRevoked(true);
    }
}
