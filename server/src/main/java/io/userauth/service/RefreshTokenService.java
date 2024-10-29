package io.userauth.service;

import java.util.UUID;

import io.userauth.presentation.exception.RefreshTokenException;


public interface RefreshTokenService {
    public void revokeToken(UUID token);
    public UUID generateToken(UUID userIdIssuer);
    public UUID getUserIdIssuer(UUID token);
    public UUID consumeToken(UUID token) throws RefreshTokenException;
}
