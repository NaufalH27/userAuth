package io.userauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.repositories.UserRepository;
import io.userauth.models.dto.user.UserDTO;
import io.userauth.models.dto.user.UserDTOMapper;
import io.userauth.models.entities.UserEntity;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDTO getUserById(int id){
        UserEntity user = repository.findById(id);
        if (user == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email){
        UserEntity user = repository.findByEmail(email);
        if (user == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserByName(String name){
        UserEntity user = repository.findByName(name);
        if (user == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(user);
    }
    

    @Override
    public void updateEmail(int id, String newEmail){
        repository.updateEmail(id, newEmail);
        
    }

    @Override
    public void deleteUser(int id){
        repository.deleteUser(id);
    }
  
    

}
