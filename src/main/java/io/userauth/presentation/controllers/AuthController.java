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
import io.userauth.presentation.dto.user.UserDTO;
import io.userauth.service.AuthService;
import io.userauth.service.JWTService;


@Controller
@RequestMapping(value = "/api/auth")
public class AuthController {
    
    private final AuthService authService;
    private final JWTService jwtService;

    @Autowired
    public AuthController(AuthService authService, JWTService jwtService){
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/login/name")
    public ResponseEntity<String> getAuthenticationByName(@RequestBody LoginUsernameDTO loginForm){
        
        UserDTO authenticatedUser = authService.authenticateByUsername(loginForm);
        String JWToken = jwtService.generateToken(authenticatedUser); 
        

        return new ResponseEntity<>(JWToken, HttpStatus.OK);
    }

    @PostMapping(value = "/login/email")
    public ResponseEntity<String> getAuthenticationByEmail(@RequestBody LoginEmailDTO loginForm){

        UserDTO authenticatedUser = authService.authenticateByEmail(loginForm);
        String JWToken = jwtService.generateToken(authenticatedUser);

        return new ResponseEntity<>(JWToken, HttpStatus.OK);
    }
}
