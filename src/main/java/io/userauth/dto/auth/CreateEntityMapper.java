package io.userauth.dto.auth;

import io.userauth.models.UserEntity;
import io.userauth.util.PasswordUtils;

public class CreateEntityMapper {
    
    public static UserEntity toEntity(UserCreationDTO creationForm){
        if(creationForm == null){
            return null;
        }
        
        UserEntity user = new UserEntity();
        user.setName(creationForm.getUsername());
        String hashedPassword = PasswordUtils.hashPassword(creationForm.getPassword());
        user.setPasswordHash(hashedPassword);

        return user;
    }
}
