package io.userauth.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.userauth.dto.auth.AuthStrategyType;
import io.userauth.dto.auth.EmailLoginForm;
import io.userauth.dto.auth.RegistrationSession;
import io.userauth.dto.auth.UserCreationForm;
import io.userauth.dto.auth.UsernameLoginForm;
import io.userauth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@Controller
@RequestMapping(value = "/auth")
public class RegistrationController {
    
    private final RegistrationServ authService;
    
    @Autowired
    public RegistrationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserCreationForm creationForm) {
        authService.createUser(creationForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping(value = "/verify")
    public ResponseEntity<?> emailVerification(@RequestBody RegistrationSession emailVerification) {
        authService.verifyEmail(emailVerification);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
