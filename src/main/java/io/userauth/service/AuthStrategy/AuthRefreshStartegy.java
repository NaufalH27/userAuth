package io.userauth.service.AuthStrategy;

import java.util.Date;

import io.userauth.constant.TokenStatus;
import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.RefreshTokenForm;
import io.userauth.mapper.AuthenticatedUserMapper;
import io.userauth.models.RefreshToken;
import io.userauth.models.Users;

public class AuthRefreshStartegy implements AuthStrategy<RefreshTokenForm> {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthRefreshStartegy(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public AuthenticatedUser getAuthentication(RefreshTokenForm loginForm) {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(loginForm.getTokenId());

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
        
        Users authenticatedUser = userRepository.findById(refreshToken.getUserId());
        return AuthenticatedUserMapper.toDTO(authenticatedUser);
    }
    
}
