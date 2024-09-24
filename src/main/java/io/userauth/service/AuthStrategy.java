package io.userauth.service;

import io.userauth.dto.auth.AuthenticatedUserDTO;

public interface AuthStrategy {
    AuthenticatedUserDTO getAuthentication(Object loginForm);
}
