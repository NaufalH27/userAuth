package io.userauth.service.auth;

import org.springframework.stereotype.Component;

import io.userauth.common.PasswordUtils;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthForm;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.EmailLoginForm;
import io.userauth.mapper.AuthenticatedUserMapper;
import io.userauth.models.Users;

@Component
public class AuthEmailStrategy implements AuthStrategy {
    
    private final UserRepository userRepository;

    public AuthEmailStrategy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticatedUser getAuthentication(AuthForm loginForm) {
        EmailLoginForm emailForm = (EmailLoginForm) loginForm;
        Users entity = userRepository.findByEmail(emailForm.getEmail());

        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        if (!PasswordUtils.verifyPassword(emailForm.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("email password");
        }
        
        return AuthenticatedUserMapper.toDTO(entity);
    }
}
