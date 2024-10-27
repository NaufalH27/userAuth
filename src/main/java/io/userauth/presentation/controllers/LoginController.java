package io.userauth.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.userauth.dto.auth.EmailLoginForm;
import io.userauth.dto.auth.UsernameLoginForm;
import io.userauth.service.AuthService;
import io.userauth.service.AuthStrategy.AuthStrategy;
import io.userauth.service.AuthStrategy.AuthStrategyFactory;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final AuthService authService;
    private final AuthStrategyFactory authStrategyFactory;

    public LoginController(AuthService authService, AuthStrategyFactory authStrategyFactory) {
        this.authService = authService;
        this.authStrategyFactory = authStrategyFactory;
    }    
    
    @PostMapping(value = "/username")
    public ResponseEntity<?> getAuthenticationByName(@RequestBody UsernameLoginForm loginForm, HttpServletResponse response) {
        AuthStrategy<UsernameLoginForm> strategy = authStrategyFactory.createAuthStrategy(UsernameLoginForm.class);
        authService.authenticate(strategy, loginForm, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/email")
    public ResponseEntity<?> getAuthenticationByEmail(@RequestBody EmailLoginForm loginForm, HttpServletResponse response) {
        AuthStrategy<EmailLoginForm> strategy = authStrategyFactory.createAuthStrategy(EmailLoginForm.class);
        authService.authenticate(strategy, loginForm, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
