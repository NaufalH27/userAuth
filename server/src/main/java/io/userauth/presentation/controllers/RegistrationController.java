package io.userauth.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.userauth.dto.auth.UserCreationForm;
import io.userauth.service.RegistrationService;
import jakarta.validation.Valid;


@RestController
public class RegistrationController {
    
    private final RegistrationService registrationService;
    
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserCreationForm creationForm) {
        registrationService.register(creationForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    // @PostMapping(value = "/verify")
    // public ResponseEntity<?> emailVerification(@RequestBody RegistrationSession emailVerification) {
    //     registrationService.verifyEmail(emailVerification);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }
}
