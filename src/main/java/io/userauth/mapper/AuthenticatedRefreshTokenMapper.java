package io.userauth.mapper;

import io.userauth.dto.auth.AuthenticatedRefreshToken;
import io.userauth.models.RefreshToken;

public class AuthenticatedRefreshTokenMapper {
    public static AuthenticatedRefreshToken toDTO(RefreshToken refreshToken) {
        AuthenticatedRefreshToken authenticatedRefreshToken = new AuthenticatedRefreshToken();
        authenticatedRefreshToken.setToken(refreshToken.getToken());
        authenticatedRefreshToken.setUserId(refreshToken.getUserId());
        return authenticatedRefreshToken;
    }
}
