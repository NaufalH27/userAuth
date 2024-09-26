package io.userauth.mapper;

import org.springframework.stereotype.Component;

import io.userauth.dto.user.UserDTO;
import io.userauth.models.UserEntity;


@Component
public class UserDTOMapper {

    public static UserDTO toDTO(UserEntity user){        
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        return dto;

    }
}
