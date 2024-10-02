package io.userauth.dto.auth;

import io.userauth.models.RefreshToken;

public interface RefreshTokenRepository {
    public void addToken(RefreshToken refreshToken);
    public void revokeToken(RefreshToken refreshToken);

}
