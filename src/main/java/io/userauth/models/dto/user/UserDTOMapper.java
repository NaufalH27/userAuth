package io.userauth.models.dto.user;

import org.springframework.stereotype.Component;

import io.userauth.models.entities.UserEntity;


@Component
public class UserDTOMapper {

    public static UserDTO toDTO(UserEntity entity){

        if (entity == null){
            return null;
        }
        
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());

        return dto;

    }
}
