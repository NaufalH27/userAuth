package io.userauth.service.AuthStrategy;

import io.userauth.constant.TokenStatus;
import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.ILoginForm;
import io.userauth.models.RefreshToken;

public class AuthRefreshStartegy implements AuthStrategy {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthRefreshStartegy(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public AuthenticatedUser getAuthentication(ILoginForm loginForm) {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(tokenId);

        if (refreshToken == null) {
            throw new IllegalArgumentException("Token not found");
        }
        
        if (new Date().after(refreshToken.getExpiredAt())) {
            refreshToken.setStatus(TokenStatus.EXPIRED);
            throw new IllegalArgumentException("expired Session");
        }
        
        if (refreshToken.getStatus() == TokenStatus.USED){
            throw new IllegalArgumentException("invalid Session");
        }
        
        if (refreshToken.getStatus() == TokenStatus.REVOKED) {
            throw new IllegalArgumentException("Session revoked");
        }
        
        
    }
    
}
