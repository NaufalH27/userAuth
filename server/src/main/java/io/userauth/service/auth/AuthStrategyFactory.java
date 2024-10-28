package io.userauth.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthForm;
import io.userauth.dto.auth.EmailLoginForm;
import io.userauth.dto.auth.RefreshTokenForm;
import io.userauth.dto.auth.UsernameLoginForm;

@Service
public class AuthStrategyFactory {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    protected AuthStrategyFactory(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository){
        this.userRepository = userRepository;  
        this.refreshTokenRepository = refreshTokenRepository;
    }
    
    public AuthStrategy createAuthStrategy(AuthForm authForm) {
        if(authForm instanceof UsernameLoginForm) {
            return new AuthUsernameStrategy(userRepository);
        }
        if(authForm instanceof EmailLoginForm) {
            return new AuthEmailStrategy(userRepository);
        }
        if(authForm instanceof RefreshTokenForm) {
            return new AuthRefreshStrategy(userRepository, refreshTokenRepository);
        }

        throw new IllegalArgumentException("unsupported authentication type");
    }

}

