package io.userauth.service;

import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.ILoginForm;

public interface AuthService {
    public AuthenticatedUser getAuthenticatedUser(AuthStrategyType type, ILoginForm loginForm);
}
