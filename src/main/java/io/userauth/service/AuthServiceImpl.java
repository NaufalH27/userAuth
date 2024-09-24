package io.userauth.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthStrategyType;
import io.userauth.dto.auth.AuthenticatedUserDTO;
import io.userauth.dto.auth.CreateEntityMapper;
import io.userauth.dto.auth.UserCreationDTO;
import io.userauth.dto.auth.emailVerifyDTO;
import io.userauth.util.CookieUtils;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(JWTService jwtService, UserRepository userRepository){
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    

    @Override
    public void createUser(UserCreationDTO creationForm){
        if(userRepository.findByName(creationForm.getUsername()) != null){
            throw new IllegalArgumentException("username already used");
        }
        userRepository.createUser(CreateEntityMapper.toEntity(creationForm));
    }

    @Override
    public void authenticate(AuthStrategyType type, Object loginForm, HttpServletResponse response){
        AuthenticatedUserDTO authenticatedUser = createAuthStrategy(type).getAuthentication(loginForm);
        String JWTToken = generateToken(authenticatedUser);
        CookieUtils.sendCookies(response, "token", JWTToken);
    }
    
    private AuthStrategy createAuthStrategy(AuthStrategyType type){
        return switch (type) {
            case USERNAME -> new AuthUsernameStrategy(userRepository);
            case EMAIL -> new AuthEmailStrategy(userRepository);
            default -> throw new IllegalArgumentException("nonexistance authentication strategy");
        };
    }

    private String generateToken(AuthenticatedUserDTO user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getName());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        return jwtService.generateToken(claims, user.getId().toString()); 
    }

    @Override
    public void verifyEmail(emailVerifyDTO verification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

