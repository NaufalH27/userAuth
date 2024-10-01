package io.userauth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import io.userauth.common.PasswordUtils;
import io.userauth.data.repositories.RoleRepository;
import io.userauth.dto.auth.UserCreationForm;
import io.userauth.models.Roles;
import io.userauth.models.Users;

public class RegistrationServiceImpl implements RegistrationService {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationServiceImpl(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(UserCreationForm creationForm) {
        if (userService.existByEmail(creationForm.getEmail())){

        }
        
        if (userService.existByUsername(creationForm.getUsername())){
            
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
