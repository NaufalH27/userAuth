package io.userauth.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.userauth.models.dto.auth.AuthStrategyType;
import io.userauth.models.dto.auth.LoginEmailDTO;
import io.userauth.models.dto.auth.LoginUsernameDTO;
import io.userauth.models.dto.auth.UserCreationDTO;
import io.userauth.models.dto.auth.emailVerifyDTO;
import io.userauth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    
    private final AuthService authService;
    
    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserCreationDTO creationForm) {
        authService.createUser(creationForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/verify")
    public ResponseEntity<?> emailVerification(@RequestBody emailVerifyDTO emailVerification){
        authService.verifyEmail(emailVerification);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login/username")
    public ResponseEntity<?> getAuthenticationByName(@RequestBody LoginUsernameDTO loginForm, HttpServletResponse response){
        authService.authenticate(AuthStrategyType.USERNAME, loginForm, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login/email")
    public ResponseEntity<?> getAuthenticationByEmail(@RequestBody LoginEmailDTO loginForm, HttpServletResponse response){
        authService.authenticate(AuthStrategyType.EMAIL, loginForm, response);     
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
