package io.userauth.service;

import java.util.UUID;

public interface RefreshTokenService {
    public void revokeToken(UUID token);
    public String generateToken();
}
