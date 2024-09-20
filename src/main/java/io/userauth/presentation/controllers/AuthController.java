package io.userauth.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.userauth.presentation.dto.auth.LoginEmailDTO;
import io.userauth.presentation.dto.auth.LoginUsernameDTO;
import io.userauth.service.AuthFactory;
import io.userauth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/api/auth")
public class AuthController {
    
    private final AuthService authService;
    private final AuthFactory authFactory;
    
    @Autowired
    public AuthController(AuthService authService, AuthFactory authFactory){
        this.authService = authService;
        this.authFactory = authFactory;
    }

    @PostMapping(value = "/login/username")
    public ResponseEntity<?> getAuthenticationByName(@RequestBody LoginUsernameDTO loginForm, HttpServletResponse response){
        authService.authenticate(authFactory.cerateAuth("username"), loginForm, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login/email")
    public ResponseEntity<?> getAuthenticationByEmail(@RequestBody LoginEmailDTO loginForm, HttpServletResponse response){
        authService.authenticate(authFactory.cerateAuth("email"), loginForm, response);     
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
