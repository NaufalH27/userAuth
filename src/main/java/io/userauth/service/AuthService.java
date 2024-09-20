package io.userauth.service;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    public void authenticate(AuthStrategy authStrategy, Object loginForm, HttpServletResponse response);
}
