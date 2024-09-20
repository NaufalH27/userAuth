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
import io.userauth.service.CookieService;
import io.userauth.service.JWTService;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/api/auth")
public class AuthController {
    
    private final AuthService authService;
    private final JWTService jwtService;
    private final CookieService cookieService;

    @Autowired
    public AuthController(AuthService authService, JWTService jwtService, CookieService cookieService){
        this.authService = authService;
        this.jwtService = jwtService;
        this.cookieService = cookieService;
    }

    @PostMapping(value = "/login/name")
    public ResponseEntity<?> getAuthenticationByName(@RequestBody LoginUsernameDTO loginForm, HttpServletResponse response){
        
        UserDTO authenticatedUser = authService.authenticateByUsername(loginForm);
        String JWTToken = jwtService.generateToken(authenticatedUser); 
        
        cookieService.sendToken(response, JWTToken);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login/email")
    public ResponseEntity<?> getAuthenticationByEmail(@RequestBody LoginEmailDTO loginForm, HttpServletResponse response){

        UserDTO authenticatedUser = authService.authenticateByEmail(loginForm);
        String JWTToken = jwtService.generateToken(authenticatedUser);
        
        cookieService.sendToken(response, JWTToken);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
