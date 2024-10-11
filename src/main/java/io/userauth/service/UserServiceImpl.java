package io.userauth.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.userauth.common.PasswordUtils;
import io.userauth.constant.RoleName;
import io.userauth.data.repositories.RoleRepository;
import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.auth.UserCreationForm;
import io.userauth.dto.user.UserDTO;
import io.userauth.mapper.UserDTOMapper;
import io.userauth.models.Roles;
import io.userauth.models.Users;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    
    @Override
    @Transactional
    public void createUser(UserCreationForm creationForm) {

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
        userRole.add(roleRepository.findRoleByName(RoleName.USER));
        registeredUser.setRoles(userRole);

        userRepository.createUser(registeredUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(UUID id) {
        Users user = userRepository.findById(id);
        if (user == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByName(String name) {
        Users user = userRepository.findByName(name);
        if (user == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(user);
    }
    

    @Override
    @Transactional
    public void updateEmail(UUID id, String newEmail) {
        Users user = userRepository.findById(id);
        user.setEmail(newEmail);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        userRepository.deleteUser(id);
    }

}
