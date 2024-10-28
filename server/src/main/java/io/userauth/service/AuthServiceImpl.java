package io.userauth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import io.userauth.constant.CookieName;
import io.userauth.dto.auth.AuthForm;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.service.auth.AuthStrategy;
import io.userauth.service.auth.AuthStrategyFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final JWTHelper jwtHelper;
    private final RefreshTokenService refreshTokenService;
    private final AuthStrategyFactory authStrategyFactory;

    @Autowired
    public AuthServiceImpl(JWTHelper jwtHelper, RefreshTokenService refreshTokenService, AuthStrategyFactory authStrategyFactory) {
        this.jwtHelper = jwtHelper;
        this.refreshTokenService = refreshTokenService;
        this.authStrategyFactory = authStrategyFactory;
    }

    @Override
    public void authenticate(AuthForm authForm, HttpServletResponse response) {
        AuthStrategy authStrategy = authStrategyFactory.createAuthStrategy(authForm);
        AuthenticatedUser user = authStrategy.getAuthentication(authForm);
        String accessToken = jwtHelper.generateAccessToken(user);
        UUID refreshToken = refreshTokenService.generateToken(user.getId());
        CookieUtils.sendCookie(response, CookieName.ACCESS_TOKEN, accessToken);
        CookieUtils.sendCookie(response, CookieName.REFRESH_TOKEN, refreshToken.toString());
    }

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtils.getCookieValue(request, CookieName.REFRESH_TOKEN);
        refreshTokenService.revokeToken(UUID.fromString(refreshToken));
        CookieUtils.eraseCookie(CookieName.ACCESS_TOKEN, response);
        CookieUtils.eraseCookie(CookieName.REFRESH_TOKEN, response);
    }
    
}
