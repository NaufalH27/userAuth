package io.userauth.service;

import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    public void login(AuthStrategyType type, Object loginForm, HttpServletResponse response);
} 


