package io.userauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.repositories.UserRepository;
import io.userauth.models.dto.user.UserCreationDTO;
import io.userauth.models.dto.user.UserDTO;
import io.userauth.models.dto.user.UserDTOMapper;
import io.userauth.models.entities.UserEntity;
import io.userauth.util.PasswordUtils;


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
    public void createUser(UserCreationDTO creationForm){

        if(repository.findByEmail(creationForm.getEmail()) != null ){
            throw new IllegalArgumentException("email already used");
        }

        if(repository.findByName(creationForm.getName()) != null){
            throw new IllegalArgumentException("username already used");
        }

        UserEntity user = new UserEntity();
        user.setName(creationForm.getName());
        user.setEmail(creationForm.getEmail());
        String hashedPassword = PasswordUtils.hashPassword(creationForm.getPassword());
        user.setPasswordHash(hashedPassword);
        repository.createUser(user);
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
