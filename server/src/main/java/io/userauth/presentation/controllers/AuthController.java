package io.userauth.presentation.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.userauth.common.CookieUtils;
import io.userauth.constant.CookieName;
import io.userauth.dto.auth.EmailLoginForm;
import io.userauth.dto.auth.UsernameLoginForm;
import io.userauth.presentation.exception.AuthException;
import io.userauth.presentation.exception.RefreshTokenException;
import io.userauth.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }    
    
    @PostMapping(value = "/login/username")
    public ResponseEntity<?> getAuthenticationByName(@RequestBody UsernameLoginForm loginForm, HttpServletResponse response) {
        try {
            authService.login(loginForm);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthException e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }        
    }

    @PostMapping(value = "/login/email")
    public ResponseEntity<?> getAuthenticationByEmail(@RequestBody EmailLoginForm loginForm, HttpServletResponse response) {
        try {
            authService.login(loginForm);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthException e) {
            return new ResponseEntity<>(HttpStatus.OK);   
        }
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String refreshToken = CookieUtils.getCookieValue(cookies, CookieName.REFRESH_TOKEN);
        authService.logout(UUID.fromString(refreshToken));
        CookieUtils.eraseCookie(CookieName.REFRESH_TOKEN, response);
        CookieUtils.eraseCookie(CookieName.ACCESS_TOKEN, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String refreshToken = CookieUtils.getCookieValue(cookies, CookieName.REFRESH_TOKEN);
        try {
            authService.regenerateNewToken(UUID.fromString(refreshToken));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(RefreshTokenException e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
