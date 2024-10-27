package io.userauth.service.AuthStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.data.repositories.UserRepository;

@Service
public class AuthStrategyFactory {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public AuthStrategyFactory(UserRepository userRepository, io.userauth.data.repositories.RefreshTokenRepository refreshTokenRepository){
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }
    
    public AuthStrategy createAuthStrategy(AuthStrategyType type) {
        return switch (type) {
            case USERNAME -> new AuthUsernameStrategy(userRepository);
            case EMAIL -> new AuthEmailStrategy(userRepository);
            case REFRESH -> new AuthRefreshStartegy(userRepository, refreshTokenRepository);
            default -> throw new IllegalArgumentException("nonexistance authentication strategy");
        };
    }

}

