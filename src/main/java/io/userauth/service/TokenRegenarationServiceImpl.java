package io.userauth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import io.userauth.constant.CookieName;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.mapper.AuthenticatedUserMapper;
import io.userauth.models.Users;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class TokenRegenarationServiceImpl implements TokenRegenarationService {

    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final JWTHelper jwtHelper;

    @Autowired
    public TokenRegenarationServiceImpl(RefreshTokenService refreshTokenService, JWTHelper jwtHelper, io.userauth.data.repositories.UserRepository userRepository) {
        this.refreshTokenService = refreshTokenService;
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public void regenerateNewToken(UUID refreshToken, HttpServletResponse response) {
        UUID userId = refreshTokenService.getUserIdIssuer(refreshToken);
        Users user = userRepository.findById(userId);

        //TODO: handling unknown error if user null and handling RefreshToken expiration and revoked refreshToken
        AuthenticatedUser authenticatedUser = AuthenticatedUserMapper.toDTO(user);
        String newAccessToken = jwtHelper.generateAccessToken(authenticatedUser);
        UUID newRefreshToken = refreshTokenService.generateToken(userId);
        CookieUtils.sendCookie(response, CookieName.ACCESS_TOKEN, newAccessToken );
        CookieUtils.sendCookie(response, CookieName.REFRESH_TOKEN, newRefreshToken.toString());

    }
}
