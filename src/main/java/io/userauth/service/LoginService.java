package io.userauth.service;

import io.userauth.dto.auth.ILoginForm;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    public void login(AuthStrategyType type, ILoginForm loginForm, HttpServletResponse response);
} 


