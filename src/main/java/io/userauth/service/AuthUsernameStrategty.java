package io.userauth.service;

import org.springframework.stereotype.Service;

import io.userauth.data.repositories.UserRepository;
import io.userauth.models.dto.auth.AuthResult;
import io.userauth.models.dto.auth.LoginUsernameDTO;
import io.userauth.models.entities.User;
import io.userauth.util.PasswordUtils;

@Service
public class AuthUsernameStrategty implements AuthStrategy {
    private final UserRepository userRepository;

    public AuthUsernameStrategty(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public AuthResult getAuthentication(Object loginForm) {
        LoginUsernameDTO form = (LoginUsernameDTO) loginForm;
        User entity = userRepository.findByName(form.getUsername());

        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        if (!PasswordUtils.verifyPassword(form.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("incorect password");
        }

        return new AuthResult();
    }



}
