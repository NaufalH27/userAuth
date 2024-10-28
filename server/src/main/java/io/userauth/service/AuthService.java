package io.userauth.service;

import io.userauth.dto.auth.AuthForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    public void authenticate(AuthForm authForm, HttpServletResponse response);
    public void logout(HttpServletRequest request, HttpServletResponse response);
} 


