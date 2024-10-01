package io.userauth.service;

import org.springframework.stereotype.Service;

import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import io.userauth.dto.auth.AuthStrategyType;
import io.userauth.dto.auth.AuthenticatedUser;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthService authService;
    private final JWTHelper jwtHelper;

    public LoginServiceImpl(AuthService authService, JWTHelper jwtHelper) {
        this.authService = authService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public void login(AuthStrategyType type, Object loginForm, HttpServletResponse response) {
        AuthenticatedUser authenticatedUser = authService.getAuthenticatedUser(type, loginForm);
        String accessToken = jwtHelper.generateAccessToken(authenticatedUser);
        String refreshToken = jwtHelper.generateRefreshToken();
        CookieUtils.sendCookies(response, "AccessToken", accessToken);
        CookieUtils.sendCookies(response, "refreshToken", refreshToken);
    }
}
