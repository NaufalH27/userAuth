package io.userauth.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.userauth.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value ="/test")
    public ResponseEntity<String> deleteUser() {
        return new ResponseEntity<>("sucessfully extracted bypass jwt", HttpStatus.OK);
    }

    @DeleteMapping(value ="/delete")
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
