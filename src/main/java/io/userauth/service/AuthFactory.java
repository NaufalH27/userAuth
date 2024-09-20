package io.userauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.userauth.data.repositories.UserRepository;
import io.userauth.mapper.UserMapper;


@Component
public class AuthFactory {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Autowired
    public AuthFactory(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public AuthStrategy cerateAuth(String param){
        if("username".equals(param)){
            return new AuthUsernameStrategty(userRepository, userMapper);
        }else if("email".equals(param)){
            return new AuthEmailStrategy(userRepository, userMapper);
        } else{
            throw new IllegalArgumentException("nonexistance authentication strategy");
        }
    }
}

