package io.userauth.service;

import io.userauth.dto.auth.AuthStrategyType;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.RegistrationSession;
import io.userauth.dto.auth.UserCreationForm;

public interface AuthService {
    public AuthenticatedUser getAuthenticatedUser(AuthStrategyType type, Object loginForm);
    public void createUser(UserCreationForm creationForm);
    public void verifyEmail(RegistrationSession verification);
}
