package io.userauth.service;

public interface AuthStrategy {
    AuthResult getAuthentication(Object loginForm);
}
