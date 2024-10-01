package io.userauth.service;

import io.userauth.dto.auth.AuthStrategyType;
import io.userauth.dto.auth.RegistrationSession;
import io.userauth.dto.auth.UserCreationForm;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    public void authenticate(AuthStrategyType type, Object loginForm, HttpServletResponse response);
    public void createUser(UserCreationForm creationForm);
    public void verifyEmail(RegistrationSession verification);
}
