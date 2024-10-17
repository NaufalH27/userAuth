package io.userauth.data.session.repositories;

import java.util.UUID;

import io.userauth.data.session.models.RefreshToken;

public interface RefreshTokenRepository {
    public RefreshToken findTokenById(UUID id);
    public void addToken(RefreshToken refreshToken);
}
