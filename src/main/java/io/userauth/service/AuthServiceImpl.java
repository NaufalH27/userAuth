package io.userauth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import io.userauth.constant.CookieName;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.service.AuthStrategy.AuthStrategy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final JWTHelper jwtHelper;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthServiceImpl(JWTHelper jwtHelper, RefreshTokenService refreshTokenService) {
        this.jwtHelper = jwtHelper;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtils.getCookieValue(request, CookieName.REFRESH_TOKEN);
        refreshTokenService.revokeToken(UUID.fromString(refreshToken));
        CookieUtils.eraseCookie(CookieName.REFRESH_TOKEN, response);
    }

    @Override
    public <T> void authenticate(AuthStrategy<T> authStrategy, T authForm, HttpServletResponse response) {
        AuthenticatedUser authenticatedUser = authStrategy.getAuthentication(authForm);
        String accessToken = jwtHelper.generateAccessToken(authenticatedUser);
        UUID refreshToken = refreshTokenService.generateToken(authenticatedUser.getId());
        CookieUtils.sendCookie(response, CookieName.ACCESS_TOKEN, accessToken);
        CookieUtils.sendCookie(response, CookieName.REFRESH_TOKEN, refreshToken.toString());
    }
    
}
