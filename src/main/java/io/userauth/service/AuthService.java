package io.userauth.service;

import io.userauth.models.dto.auth.AuthStrategyType;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    public void authenticate(AuthStrategyType type, Object loginForm, HttpServletResponse response);
}
