package io.userauth.mapper;

import org.springframework.stereotype.Component;

import io.userauth.dto.user.UserDTO;
import io.userauth.models.UserEntity;


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
