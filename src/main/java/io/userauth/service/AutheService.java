package io.userauth.service;

import io.userauth.dto.auth.ILoginForm;
import io.userauth.service.AuthStrategy.AuthStrategyType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AutheService {
    public void login(AuthStrategyType type, ILoginForm loginForm, HttpServletResponse response);
    public void logout(HttpServletRequest request, HttpServletResponse response);
} 


