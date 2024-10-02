package io.userauth.data.repositories;

import org.springframework.stereotype.Repository;

import io.userauth.models.RefreshToken;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository{

    @Override
    public void addToken(RefreshToken refreshToken) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void revokeToken(RefreshToken refreshToken) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
