package io.userauth.service;

import java.util.UUID;

import io.userauth.dto.auth.AuthenticatedRefreshToken;

public interface RefreshTokenService {
    public void revokeToken(UUID token);
    public UUID generateToken(UUID userIdIssuer);
    public UUID getUserIdIssuer(UUID token);
    public AuthenticatedRefreshToken getAuthenticatedRefreshToken(UUID tokenId);
}
