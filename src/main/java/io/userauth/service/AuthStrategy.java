package io.userauth.service;

import io.userauth.presentation.dto.user.UserDTO;

public interface AuthStrategy {
    UserDTO getAuthentication(Object loginForm);
}
