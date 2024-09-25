package io.userauth.dto.auth;

import io.userauth.common.PasswordUtils;
import io.userauth.models.UserEntity;

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
