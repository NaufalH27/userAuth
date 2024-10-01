package io.userauth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import io.userauth.common.PasswordUtils;
import io.userauth.data.repositories.RoleRepository;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.UserCreationForm;
import io.userauth.models.Roles;
import io.userauth.models.Users;

public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(UserCreationForm creationForm) {

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

}
