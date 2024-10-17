package io.userauth.data.repositories;

import java.util.UUID;

import io.userauth.models.RefreshToken;

public class RefreshTokenRepositoryRedisImpl implements RefreshTokenRepository {

    @Override
    public RefreshToken findTokenById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addToken(RefreshToken refreshToken) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
