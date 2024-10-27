package io.userauth.service.AuthStrategy;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.EmailLoginForm;
import io.userauth.dto.auth.UsernameLoginForm;

@Service
public class AuthStrategyFactory {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public AuthStrategyFactory(UserRepository userRepository, io.userauth.data.repositories.RefreshTokenRepository refreshTokenRepository){
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }
    
    public <T> AuthStrategy createAuthStrategy(Class<T> clazz) {
        if (clazz.equals(UsernameLoginForm.class)){
            return new AuthUsernameStrategy(userRepository);
        }
        if (clazz.equals(EmailLoginForm.class)){
            return new AuthEmailStrategy(userRepository);
        }
        if (clazz.equals(UUID.class)){
            return new AuthRefreshStartegy(userRepository, refreshTokenRepository);
        }
        return null;
    }

}

