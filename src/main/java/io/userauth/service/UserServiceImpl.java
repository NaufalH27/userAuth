package io.userauth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.repositories.UserRepository;
import io.userauth.dto.user.UserDTO;
import io.userauth.mapper.UserDTOMapper;
import io.userauth.models.Users;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(UUID id) {
        Users user = userRepository.findById(id);
        if (user == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserByName(String name) {
        Users user = userRepository.findByName(name);
        if (user == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(user);
    }
    

    @Override
    public void updateEmail(int id, String newEmail) {
        userRepository.updateEmail(id, newEmail);
        
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    @Override
    public Boolean existByUsername(String username) {
        return userRepository.findByName(username) == null;
     
    }

    @Override
    public Boolean existByEmail(String email) {
        return userRepository.findByEmail(email) == null;
    }
  

}
