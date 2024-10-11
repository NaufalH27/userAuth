package io.userauth.service;



import org.springframework.stereotype.Service;

import io.userauth.common.PasswordUtils;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.ILoginForm;
import io.userauth.mapper.AuthenticatedUserMapper;
import io.userauth.models.Users;

@Service
public class AuthEmailStrategy implements AuthStrategy{
    
    private final UserRepository userRepository;

    public AuthEmailStrategy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticatedUser getAuthentication(ILoginForm loginForm) {
        Users entity = userRepository.findByEmail(loginForm.getIdentifier());

        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        if (!PasswordUtils.verifyPassword(loginForm.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("email password");
        }
        
        return AuthenticatedUserMapper.toDTO(entity);
    }
}
