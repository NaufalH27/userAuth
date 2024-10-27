package io.userauth.service.AuthStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.repositories.UserRepository;

@Service
public class AuthStrategyFactory {

    private final UserRepository userRepository;

    @Autowired
    public AuthStrategyFactory(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public AuthStrategy createAuthStrategy(AuthStrategyType type) {
        return switch (type) {
            case USERNAME -> new AuthUsernameStrategy(userRepository);
            case EMAIL -> new AuthEmailStrategy(userRepository);
            case REFRESH -> new AuthRefreshStartegy(userRepository);
            default -> throw new IllegalArgumentException("nonexistance authentication strategy");
        };
    }

}

