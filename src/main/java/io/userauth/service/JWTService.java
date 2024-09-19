package io.userauth.service;

import io.userauth.presentation.dto.user.UserDTO;

public interface JWTService {
    public String generateToken(UserDTO user);
}
