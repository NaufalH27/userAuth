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
    public String generateToken(){
        return UUID.randomUUID().toString();
    }

    @Override
    @Transactional
    public void revokeToken(UUID token) {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(token);
        refreshToken.setIsRevoked(true);
    }
}
