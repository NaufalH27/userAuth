package io.userauth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import io.userauth.common.PasswordUtils;
import io.userauth.constant.CookieName;
import io.userauth.constant.UserStatus;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthForm;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.EmailLoginForm;
import io.userauth.dto.auth.UsernameLoginForm;
import io.userauth.mapper.AuthErrorCodeMapper;
import io.userauth.mapper.AuthenticatedUserMapper;
import io.userauth.models.Users;
import io.userauth.presentation.exception.AuthErrorCode;
import io.userauth.presentation.exception.AuthException;
import io.userauth.presentation.exception.RefreshTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

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
    public void login(AuthForm authForm, HttpServletResponse response) throws AuthException {
        AuthenticatedUser user = this.getAuthenticatedUser(authForm);
        this.sendToken(user, response);
    }

    @Override
    @Transactional 
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtils.getCookieValue(request, CookieName.REFRESH_TOKEN);
        refreshTokenService.revokeToken(UUID.fromString(refreshToken));
        CookieUtils.eraseCookie(CookieName.ACCESS_TOKEN, response);
        CookieUtils.eraseCookie(CookieName.REFRESH_TOKEN, response);
    }

    @Override
    public void regenerateNewToken(UUID refreshToken, HttpServletResponse response) throws RefreshTokenException {
        UUID userId = refreshTokenService.useToken(refreshToken);
        Users user = userRepository.findById(userId);
        AuthenticatedUser authenticatedUser = AuthenticatedUserMapper.toDTO(user);
        this.sendToken(authenticatedUser, response);
    }
   
    private AuthenticatedUser getAuthenticatedUser(AuthForm authForm) throws AuthException {
        Users user = switch (authForm) {
            case UsernameLoginForm form -> userRepository.findByName(form.getUsername());
            case EmailLoginForm form -> userRepository.findByEmail(form.getEmail());
            default -> throw new AuthException(AuthErrorCode.UNSUPPORTED_AUTH, "Unsupported authentication type");
        };
        
        if (!PasswordUtils.verifyPassword(authForm.getPassword(), user.getPasswordHash())){
            throw new AuthException(AuthErrorCode.INVALID_PASSWORD, "Invalid Login Password");
        }

        if(user.getStatus() != UserStatus.ACTIVE) {
            AuthErrorCode userErrorCode = AuthErrorCodeMapper.fromUserStatus(user.getStatus());
            throw new AuthException(userErrorCode, "User is not active");
        }

        return AuthenticatedUserMapper.toDTO(user);
    }

    private void sendToken(AuthenticatedUser user, HttpServletResponse response) {
        String accessToken = jwtHelper.generateAccessToken(user);
        UUID refreshToken = refreshTokenService.generateToken(user.getId());
        CookieUtils.sendCookie(response, CookieName.ACCESS_TOKEN, accessToken);
        CookieUtils.sendCookie(response, CookieName.REFRESH_TOKEN, refreshToken.toString());
    }

}
