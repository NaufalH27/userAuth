package io.userauth.service;


import org.springframework.stereotype.Service;

import io.userauth.models.dto.auth.AuthResult;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final CookieService cookieService;
        
    public AuthServiceImpl(JWTService jwtService, CookieService cookieService){
        this.jwtService = jwtService;
        this.cookieService = cookieService;
    }

    @Override
    public void authenticate(AuthStrategy authStrategy, Object loginForm, HttpServletResponse response){
        AuthResult user = authStrategy.getAuthentication(loginForm);
        String JWTToken = jwtService.generateToken(user);
        cookieService.sendToken(response, JWTToken) ;
    }
}
