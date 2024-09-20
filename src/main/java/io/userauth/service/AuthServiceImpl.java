package io.userauth.service;

import org.springframework.stereotype.Service;

import io.userauth.data.entities.UserEntity;
import io.userauth.data.repositories.UserRepository;
import io.userauth.mapper.UserMapper;
import io.userauth.presentation.dto.auth.LoginEmailDTO;
import io.userauth.presentation.dto.auth.LoginUsernameDTO;
import io.userauth.presentation.dto.user.UserDTO;
import io.userauth.util.PasswordUtils;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO authenticateByUsername(LoginUsernameDTO loginForm) {

        UserEntity entity = userRepository.findByName(loginForm.getUsername());

        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }

        if (!PasswordUtils.verifyPassword(loginForm.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("incorect password");
        }
    
       return userMapper.toDTO(entity);
    }

    @Override
    public UserDTO authenticateByEmail(LoginEmailDTO loginForm) {

        UserEntity entity = userRepository.findByEmail(loginForm.getemail());

        if (entity == null){
            throw new IllegalArgumentException("email not found");
        }

        if (!PasswordUtils.verifyPassword(loginForm.getPassword(), entity.getPasswordHash())){
            throw new IllegalArgumentException("email password");
        }

        return userMapper.toDTO(entity);    
    }



}
