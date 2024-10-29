package io.userauth.service;

import java.util.UUID;

import io.userauth.dto.auth.AuthForm;
import io.userauth.dto.auth.TokenDTO;
import io.userauth.presentation.exception.AuthException;
import io.userauth.presentation.exception.RefreshTokenException;

public interface AuthService {
    public TokenDTO login(AuthForm authForm) throws AuthException;
    public void logout(UUID refreshToken);
    public TokenDTO regenerateNewToken(UUID refreshToken) throws RefreshTokenException;
} 


