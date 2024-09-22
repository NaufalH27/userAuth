package io.userauth.service;

import io.userauth.models.dto.auth.AuthResult;

public interface AuthStrategy {
    AuthResult getAuthentication(Object loginForm);
}
