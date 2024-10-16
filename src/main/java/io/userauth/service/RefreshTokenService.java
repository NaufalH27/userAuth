package io.userauth.service;

import java.util.UUID;

public interface RefreshTokenService {
    public void revokeToken(UUID token);
    public UUID generateToken(UUID userIdIssuer);
    public UUID getUserIdIssuer(UUID token);
}
