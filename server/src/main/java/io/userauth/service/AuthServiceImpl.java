package io.userauth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import io.userauth.common.PasswordUtils;
import io.userauth.constant.CookieName;
import io.userauth.constant.UserStatus;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthForm;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.EmailLoginForm;
import io.userauth.dto.auth.TokenDTO;
import io.userauth.dto.auth.UsernameLoginForm;
import io.userauth.mapper.AuthenticatedUserMapper;
import io.userauth.models.Users;
import io.userauth.presentation.exception.AuthErrorCode;
import io.userauth.presentation.exception.AuthException;
import io.userauth.presentation.exception.RefreshTokenException;
import jakarta.servlet.http.Cookie;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JWTHelper jwtHelper;
    private final RefreshTokenService refreshTokenService;
    
    @Autowired
    public AuthServiceImpl(UserRepository userRepository, JWTHelper jwtHelper, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
        this.refreshTokenService = refreshTokenService;
    }
   
    @Override
    @Transactional(readOnly = true)
    public TokenDTO login(AuthForm authForm) throws AuthException {
        Users user = switch (authForm) {
            case UsernameLoginForm form -> userRepository.findByName(form.getUsername());
            case EmailLoginForm form -> userRepository.findByEmail(form.getEmail());
            default -> throw new AuthException(AuthErrorCode.UNSUPPORTED_AUTH, "Unsupported authentication type");
        };
        
        if(user == null) {
            throw new AuthException(AuthErrorCode.USER_NOT_FOUND, "User not found");
        }

        if (!PasswordUtils.verifyPassword(authForm.getPassword(), user.getPasswordHash())){
            throw new AuthException(AuthErrorCode.INVALID_PASSWORD, "Invalid Login Password");
        }

        if(user.getStatus() != UserStatus.ACTIVE) {
            throw new AuthException(AuthErrorCode.fromUserStatus(user.getStatus()), "User is not active");
        }

        AuthenticatedUser authenticatedUser = AuthenticatedUserMapper.toDTO(user);
        return createTokenCookies(authenticatedUser);
    }

    @Override
    @Transactional 
    public void logout(UUID refreshToken) {
        refreshTokenService.revokeToken(refreshToken);
    }

    @Override
    @Transactional
    public TokenDTO regenerateNewToken(UUID refreshToken) throws RefreshTokenException {
        UUID userId = refreshTokenService.consumeToken(refreshToken);
        Users user = userRepository.findById(userId);
        AuthenticatedUser authenticatedUser = AuthenticatedUserMapper.toDTO(user);
        return createTokenCookies(authenticatedUser);
    }
   
    private TokenDTO createTokenCookies(AuthenticatedUser user) {
        String accessToken = jwtHelper.generateAccessToken(user);
        UUID refreshToken = refreshTokenService.generateToken(user.getId());
        Cookie accessTokenCookie = CookieUtils.createCookie(CookieName.ACCESS_TOKEN, accessToken);
        Cookie refreshTokenCookie = CookieUtils.createCookie(CookieName.REFRESH_TOKEN, refreshToken.toString());
        TokenDTO token = new TokenDTO();
        token.setAccessToken(accessTokenCookie);
        token.setRefreshToken(refreshTokenCookie);
        return token;
    }
  
}
