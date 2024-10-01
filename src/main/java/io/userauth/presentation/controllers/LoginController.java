package io.userauth.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.userauth.dto.auth.EmailLoginForm;
import io.userauth.dto.auth.UsernameLoginForm;
import io.userauth.service.AuthStrategyType;
import io.userauth.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(io.userauth.service.LoginService loginService) {
        this.loginService = loginService;
    }    
    
    @PostMapping(value = "/username")
    public ResponseEntity<?> getAuthenticationByName(@RequestBody UsernameLoginForm loginForm, HttpServletResponse response) {
        loginService.login(AuthStrategyType.USERNAME, loginForm, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/email")
    public ResponseEntity<?> getAuthenticationByEmail(@RequestBody EmailLoginForm loginForm, HttpServletResponse response) {
        loginService.login(AuthStrategyType.EMAIL, loginForm, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
