package io.userauth.service.auth;

import org.springframework.stereotype.Component;

import io.userauth.common.PasswordUtils;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthForm;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.UsernameLoginForm;
import io.userauth.mapper.AuthenticatedUserMapper;
import io.userauth.models.Users;

@Component
public class AuthUsernameStrategy implements AuthStrategy {
    
    private final UserRepository userRepository;

    public AuthUsernameStrategy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticatedUser getAuthentication(AuthForm loginForm) {
        UsernameLoginForm usernameForm = (UsernameLoginForm) loginForm;
        Users entity = userRepository.findByName(usernameForm.getUsername());

        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        if (!PasswordUtils.verifyPassword(usernameForm.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("email password");
        }

        return AuthenticatedUserMapper.toDTO(entity);
    }

}
