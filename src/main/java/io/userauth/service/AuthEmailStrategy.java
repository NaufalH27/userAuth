package io.userauth.service;



import io.userauth.common.PasswordUtils;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthenticatedUserDTO;
import io.userauth.dto.auth.LoginEmailDTO;
import io.userauth.mapper.AuthenticatedUserDTOMapper;
import io.userauth.models.UserEntity;


public class AuthEmailStrategy implements AuthStrategy{
    
    private final UserRepository userRepository;

    public AuthEmailStrategy(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticatedUserDTO getAuthentication(Object loginForm) {
        LoginEmailDTO form = (LoginEmailDTO) loginForm;
        UserEntity entity = userRepository.findByEmail(form.getEmail());

        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        if (!PasswordUtils.verifyPassword(form.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("email password");
        }
        
        return AuthenticatedUserDTOMapper.toDTO(entity);
    }
}
