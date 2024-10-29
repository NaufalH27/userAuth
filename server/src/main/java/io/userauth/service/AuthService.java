package io.userauth.service;

import java.util.UUID;

import io.userauth.dto.auth.AuthForm;
import io.userauth.presentation.exception.AuthException;
import io.userauth.presentation.exception.RefreshTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    public void login(AuthForm authForm, HttpServletResponse response) throws AuthException;
    public void logout(HttpServletRequest request, HttpServletResponse response);
    public void regenerateNewToken(UUID refreshToken, HttpServletResponse response) throws RefreshTokenException;
} 


