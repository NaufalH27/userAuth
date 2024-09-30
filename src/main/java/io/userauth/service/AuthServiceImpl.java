package io.userauth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.common.PasswordUtils;
import io.userauth.data.repositories.RoleRepository;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.AuthStrategyType;
import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.RegistrationSession;
import io.userauth.dto.auth.UserCreationForm;
import io.userauth.models.Roles;
import io.userauth.models.Users;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
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
        userRole.add(roleRepository.getUserRole());
        registeredUser.setRoles(userRole);

        userRepository.createUser(registeredUser);
    }

    @Override
    public AuthenticatedUser getAuthenticatedUser(AuthStrategyType type, Object loginForm){
        return createAuthStrategy(type).getAuthentication(loginForm);
    }
    
    private AuthStrategy createAuthStrategy(AuthStrategyType type) {
        return switch (type) {
            case USERNAME -> new AuthUsernameStrategy(userRepository);
            case EMAIL -> new AuthEmailStrategy(userRepository);
            default -> throw new IllegalArgumentException("nonexistance authentication strategy");
        };
    }

    @Override
    public void verifyEmail(RegistrationSession verification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

