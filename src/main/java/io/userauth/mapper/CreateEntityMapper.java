package io.userauth.mapper;

import io.userauth.common.PasswordUtils;
import io.userauth.dto.auth.UserCreationDTO;
import io.userauth.models.UserEntity;


public class CreateEntityMapper {
    
    public static UserEntity toEntity(UserCreationDTO creationForm){        
        UserEntity user = new UserEntity();
        user.setName(creationForm.getUsername());
        String hashedPassword = PasswordUtils.hashPassword(creationForm.getPassword());
        user.setPasswordHash(hashedPassword);

        return user;
    }
}
