package io.userauth.service;

import java.util.UUID;

import jakarta.servlet.http.HttpServletResponse;

public interface TokenRegenarationService {
    public void regenerateNewToken(UUID refreshToken, HttpServletResponse response);
}
