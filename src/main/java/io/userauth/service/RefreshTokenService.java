package io.userauth.service;

import java.util.UUID;

import io.userauth.models.RefreshToken;

public interface RefreshTokenService {
    public RefreshToken revokeToken(UUID token);
    public String generateToken();
}
