package io.userauth.service;

import io.userauth.presentation.dto.user.UserDTO;

public class AuthTokenStrategy implements AuthStrategy {

    @Override
    public UserDTO getAuthentication(Object loginForm) {
        String token = (String) loginForm;

        


    }
    
}
