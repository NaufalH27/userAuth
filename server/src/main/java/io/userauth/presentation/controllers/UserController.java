package io.userauth.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.userauth.dto.auth.CustomUserDetails;
import io.userauth.dto.user.UserDTO;
import io.userauth.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value ="/me")
    public ResponseEntity<UserDTO> getMeProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserDTO user = userService.getUserById(userDetails.getId()); 
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value ="/delete")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails, UserDTO newData) {
        userService.updateUser(userDetails.getId(), newData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}