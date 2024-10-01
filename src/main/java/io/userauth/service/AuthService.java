package io.userauth.service;

import io.userauth.dto.auth.AuthStrategyType;
import io.userauth.dto.auth.AuthenticatedUser;

public interface AuthService {
    public AuthenticatedUser getAuthenticatedUser(AuthStrategyType type, Object loginForm);
}
