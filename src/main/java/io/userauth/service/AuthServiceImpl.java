package io.userauth.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.userauth.data.repositories.UserRepository;
import io.userauth.models.dto.auth.AuthStrategyType;
import io.userauth.models.dto.auth.AuthenticatedUserDTO;
import io.userauth.models.dto.auth.CreateEntityMapper;
import io.userauth.models.dto.auth.UserCreationDTO;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final CookieService cookieService;
    private final UserRepository userRepository;

        
    public AuthServiceImpl(JWTService jwtService, CookieService cookieService, UserRepository userRepository){
        this.jwtService = jwtService;
        this.cookieService = cookieService;
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserCreationDTO creationForm){
        if(userRepository.findByName(creationForm.getName()) != null){
            throw new IllegalArgumentException("username already used");
        }
        userRepository.createUser(CreateEntityMapper.toEntity(creationForm));
    }

    @Override
    public void authenticate(AuthStrategyType type, Object loginForm, HttpServletResponse response){
        AuthenticatedUserDTO authenticatedUser = createAuthStrategy(type).getAuthentication(loginForm);
        String JWTToken = generateToken(authenticatedUser);
        cookieService.sendToken(response, JWTToken);
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
}

