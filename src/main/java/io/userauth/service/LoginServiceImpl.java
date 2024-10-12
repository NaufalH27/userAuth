package io.userauth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import io.userauth.constant.CookieName;
import io.userauth.constant.JWTClaimName;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.ILoginForm;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthStrategyFactory authStrategyFactory;
    private final JWTHelper jwtHelper;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public LoginServiceImpl(AuthStrategyFactory authStrategyFactory, JWTHelper jwtHelper,RefreshTokenService refreshTokenService) {
        this.authStrategyFactory = authStrategyFactory;
        this.jwtHelper = jwtHelper;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void login(AuthStrategyType type, ILoginForm loginForm, HttpServletResponse response) {
        AuthStrategy authStrategy = authStrategyFactory.createAuthStrategy(type);
        AuthenticatedUser authenticatedUser = authStrategy.getAuthentication(loginForm);
        String accessToken = generateAccessToken(authenticatedUser);
        UUID refreshToken = refreshTokenService.generateToken(authenticatedUser.getId());
        CookieUtils.sendCookies(response, CookieName.ACCESS_TOKEN, accessToken);
        CookieUtils.sendCookies(response, CookieName.REFRESH_TOKEN, refreshToken.toString());
    }

    private String generateAccessToken(AuthenticatedUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JWTClaimName.ID, user.getId().toString());
        claims.put(JWTClaimName.EMAIL, user.getEmail());
        claims.put(JWTClaimName.ROLES, user.getRole());
        String subject = user.getUsername();
        return jwtHelper.buildAccessToken(claims, subject);
    }
    
}
