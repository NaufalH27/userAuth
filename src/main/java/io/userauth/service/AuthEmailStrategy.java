package io.userauth.service;



import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthenticatedUserDTO;
import io.userauth.dto.auth.AuthenticatedUserDTOMapper;
import io.userauth.dto.auth.LoginEmailDTO;
import io.userauth.models.entities.UserEntity;
import io.userauth.util.PasswordUtils;


public class AuthEmailStrategy implements AuthStrategy{
    
    private final UserRepository userRepository;

    public AuthEmailStrategy(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticatedUserDTO getAuthentication(Object loginForm) {
        LoginEmailDTO form = (LoginEmailDTO) loginForm;
        UserEntity entity = userRepository.findByEmail(form.getemail());

        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        if (!PasswordUtils.verifyPassword(form.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("email password");
        }
        
        return AuthenticatedUserDTOMapper.toDTO(entity);
    }
}
