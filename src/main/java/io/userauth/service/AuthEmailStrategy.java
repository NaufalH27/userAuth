package io.userauth.service;


import io.userauth.data.repositories.UserRepository;
import io.userauth.models.dto.auth.AuthResult;
import io.userauth.models.dto.auth.LoginEmailDTO;
import io.userauth.models.entities.User;
import io.userauth.util.PasswordUtils;


public class AuthEmailStrategy implements AuthStrategy{
    
    private final UserRepository userRepository;

    public AuthEmailStrategy(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public AuthResult getAuthentication(Object loginForm) {
        LoginEmailDTO form = (LoginEmailDTO) loginForm;

        User entity = userRepository.findByEmail(form.getemail());

        if (entity == null){
            throw new IllegalArgumentException("email not found");
        }
        if (!PasswordUtils.verifyPassword(form.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("email password");
        }

        
        return new AuthResult();
    }
}
