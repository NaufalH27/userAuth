package io.userauth.service;

import io.userauth.service.AuthStrategy.AuthStrategy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    public <T> void authenticate(AuthStrategy<T> authStrategy, T authForm, HttpServletResponse response);
    public void logout(HttpServletRequest request, HttpServletResponse response);
} 


