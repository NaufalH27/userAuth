package io.userauth.service;

import io.userauth.dto.auth.AuthenticatedUser;

public interface AuthStrategy {
    AuthenticatedUser getAuthentication(Object loginForm);
}
