package io.userauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.userauth.data.entities.UserEntity;
import io.userauth.data.repositories.UserRepository;
import io.userauth.mapper.Mapper;
import io.userauth.presentation.dto.user.UserCreationDTO;
import io.userauth.presentation.dto.user.UserDTO;
import io.userauth.util.PasswordUtils;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final Mapper<UserEntity, UserDTO> mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, Mapper<UserEntity, UserDTO> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO getUserById(int id){
        UserEntity entity = repository.findById(id);
        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        return mapper.toDTO(entity);
    }

    @Override
    public UserDTO getUserByEmail(String email){
        UserEntity entity = repository.findByEmail(email);
        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        return mapper.toDTO(entity);
    }

    @Override
    public UserDTO getUserByName(String name){
        UserEntity entity = repository.findByName(name);
        if (entity == null){
            throw new IllegalArgumentException("user not found");
        }
        return mapper.toDTO(entity);
    }
    

    @Override
    public void createUser(UserCreationDTO creationForm){

        if(repository.findByEmail(creationForm.getEmail()) != null ){
            throw new IllegalArgumentException("email already used");
        }

        if(repository.findByName(creationForm.getName()) != null){
            throw new IllegalArgumentException("username already used");
        }

        UserEntity entity = new UserEntity();
        entity.setName(creationForm.getName());
        entity.setEmail(creationForm.getEmail());
        String hashedPassword = PasswordUtils.hashPassword(creationForm.getPassword());
        entity.setPasswordHash(hashedPassword);
        repository.save(entity);
    }


    

}
