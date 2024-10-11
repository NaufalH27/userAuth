package io.userauth.data.repositories;

import java.util.UUID;

import io.userauth.models.RefreshToken;

public interface RefreshTokenRepository {
    public RefreshToken findTokenById(UUID id);
    public void addToken(RefreshToken refreshToken);
}
