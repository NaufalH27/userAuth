package io.userauth.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.userauth.presentation.dto.user.UserCreationDTO;
import io.userauth.presentation.dto.user.UserDTO;
import io.userauth.service.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/api/users")
public class UserController {
    
    private final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserCreationDTO creationForm) {
        service.createUser(creationForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value ="/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        service.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/update/email")
    public ResponseEntity<Void> updateEmail(@PathVariable int id, String newEmail) {
        service.updateEmail(id, newEmail);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
