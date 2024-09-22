package io.userauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.repositories.UserRepository;
import io.userauth.models.dto.user.UserCreationDTO;
import io.userauth.models.dto.user.UserDTO;
import io.userauth.models.dto.user.UserDTOMapper;
import io.userauth.models.entities.User;
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
        User entity = repository.findById(id);
        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(entity);
    }

    @Override
    public UserDTO getUserByEmail(String email){
        User entity = repository.findByEmail(email);
        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(entity);
    }

    @Override
    public UserDTO getUserByName(String name){
        User entity = repository.findByName(name);
        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        return UserDTOMapper.toDTO(entity);
    }
    

    @Override
    public void createUser(UserCreationDTO creationForm){

        if(repository.findByEmail(creationForm.getEmail()) != null ){
            throw new IllegalArgumentException("email already used");
        }

        if(repository.findByName(creationForm.getName()) != null){
            throw new IllegalArgumentException("username already used");
        }

        User entity = new User();
        entity.setName(creationForm.getName());
        entity.setEmail(creationForm.getEmail());
        String hashedPassword = PasswordUtils.hashPassword(creationForm.getPassword());
        entity.setPasswordHash(hashedPassword);
        repository.createUser(entity);
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
