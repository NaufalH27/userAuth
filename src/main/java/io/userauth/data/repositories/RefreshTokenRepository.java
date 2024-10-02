package io.userauth.data.repositories;

import io.userauth.models.RefreshToken;

public interface RefreshTokenRepository {
    public void addToken(RefreshToken refreshToken);
    public void revokeToken(RefreshToken refreshToken);

}
