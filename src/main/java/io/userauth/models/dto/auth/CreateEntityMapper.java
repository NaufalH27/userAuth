package io.userauth.models.dto.auth;

import io.userauth.models.entities.UserEntity;
import io.userauth.util.PasswordUtils;

public class CreateEntityMapper {
    
    public static UserEntity toEntity(UserCreationDTO creationForm){
        if(creationForm == null){
            return null;
        }
        
        UserEntity user = new UserEntity();
        user.setName(creationForm.getName());
        String hashedPassword = PasswordUtils.hashPassword(creationForm.getPassword());
        user.setPasswordHash(hashedPassword);

        return user;
    }
}
