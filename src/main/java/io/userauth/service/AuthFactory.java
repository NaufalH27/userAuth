package io.userauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.userauth.data.repositories.UserRepository;


@Component
public class AuthFactory {

    private final UserRepository userRepository;
    
    @Autowired
    public AuthFactory(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public AuthStrategy createAuth(String param){
        if("username".equals(param)){
            return new AuthUsernameStrategty(userRepository);
        }else if("email".equals(param)){
            return new AuthEmailStrategy(userRepository);
        } else{
            throw new IllegalArgumentException("nonexistance authentication strategy");
        }
    }
}

