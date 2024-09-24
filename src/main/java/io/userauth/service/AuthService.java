package io.userauth.service;

import io.userauth.models.dto.auth.AuthStrategyType;
import io.userauth.models.dto.auth.UserCreationDTO;
import io.userauth.models.dto.auth.emailVerifyDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    public void authenticate(AuthStrategyType type, Object loginForm, HttpServletResponse response);
    public void createUser(UserCreationDTO creationForm);
    public void verifyEmail(emailVerifyDTO verification);
}
