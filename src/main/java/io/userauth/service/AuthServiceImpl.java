package io.userauth.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import io.userauth.common.PasswordUtils;
import io.userauth.data.repositories.RoleRepository;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthStrategyType;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.UserCreationForm;
import io.userauth.dto.auth.RegistrationSession;
import io.userauth.models.Roles;
import io.userauth.models.Users;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class AuthServiceImpl implements AuthService {

    private final JWTHelper jwtHelper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(JWTHelper jwtHelper, UserRepository userRepository, RoleRepository roleRepository){
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void createUser(UserCreationForm creationForm){
        if(userRepository.findByName(creationForm.getUsername()) != null){
            throw new IllegalArgumentException("username already used");
        }
        if(userRepository.findByEmail(creationForm.getEmail()) != null){
            throw new IllegalArgumentException("email already used");
        }

        Users registeredUser = new Users();
        registeredUser.setUsername(creationForm.getUsername());
        registeredUser.setEmail(creationForm.getEmail());
        registeredUser.setPasswordHash(PasswordUtils.hashPassword(creationForm.getPassword()));
        
        Set<Roles> userRole = new HashSet<>();
        userRole.add(roleRepository.getAdminRole());
        userRole.add(roleRepository.getUserRole());
        registeredUser.setRoles(userRole);
        

        userRepository.createUser(registeredUser);
    }

    @Override
    public void authenticate(AuthStrategyType type, Object loginForm, HttpServletResponse response){
        AuthenticatedUser authenticatedUser = createAuthStrategy(type).getAuthentication(loginForm);
        String JWTToken = generateToken(authenticatedUser);
        CookieUtils.sendCookies(response, "token", JWTToken);
    }
    
    private AuthStrategy createAuthStrategy(AuthStrategyType type) {
        return switch (type) {
            case USERNAME -> new AuthUsernameStrategy(userRepository);
            case EMAIL -> new AuthEmailStrategy(userRepository);
            default -> throw new IllegalArgumentException("nonexistance authentication strategy");
        };
    }

    private String generateToken(AuthenticatedUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId().toString());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        String subject = user.getUsername();
        return jwtHelper.generateToken(claims, subject);
    }

    @Override
    public void verifyEmail(RegistrationSession verification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

