package io.userauth.service;

import io.userauth.models.dto.auth.AuthenticatedUserDTO;

public interface AuthStrategy {
    AuthenticatedUserDTO getAuthentication(Object loginForm);
}
