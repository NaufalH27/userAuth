package io.userauth.service;

import org.springframework.stereotype.Service;

import io.userauth.data.repositories.UserRepository;
import io.userauth.models.dto.auth.AuthenticatedUserDTO;
import io.userauth.models.dto.auth.AuthenticatedUserDTOMapper;
import io.userauth.models.dto.auth.LoginUsernameDTO;
import io.userauth.models.entities.User;
import io.userauth.util.PasswordUtils;

@Service
public class AuthUsernameStrategy implements AuthStrategy {
    
    private final UserRepository userRepository;

    public AuthUsernameStrategy(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticatedUserDTO getAuthentication(Object loginForm) {
        LoginUsernameDTO form = (LoginUsernameDTO) loginForm;
        User entity = userRepository.findByName(form.getUsername());

        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        if (!PasswordUtils.verifyPassword(form.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("email password");
        }

        return AuthenticatedUserDTOMapper.toDTO(entity);
    }

}
