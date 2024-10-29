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
import io.userauth.service.AuthService;
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
        authService.login(loginForm, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login/email")
    public ResponseEntity<?> getAuthenticationByEmail(@RequestBody EmailLoginForm loginForm, HttpServletResponse response) {
        authService.login(loginForm, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<?> refreshSession(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtils.getCookieValue(request, CookieName.REFRESH_TOKEN);
        authService.regenerateNewToken(UUID.fromString(refreshToken), response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
