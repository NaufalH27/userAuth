package io.userauth.service;

import org.springframework.stereotype.Service;

import io.userauth.data.entities.UserEntity;
import io.userauth.data.repositories.UserRepository;
import io.userauth.mapper.UserMapper;
import io.userauth.presentation.dto.auth.LoginUsernameDTO;
import io.userauth.presentation.dto.user.UserDTO;
import io.userauth.util.PasswordUtils;

@Service
public class AuthUsernameStrategty implements AuthStrategy {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthUsernameStrategty(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getAuthentication(Object loginForm) {
        LoginUsernameDTO form = (LoginUsernameDTO) loginForm;
        UserEntity entity = userRepository.findByName(form.getUsername());

        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        if (!PasswordUtils.verifyPassword(form.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("incorect password");
        }
    
       return userMapper.toDTO(entity);
    }



}
