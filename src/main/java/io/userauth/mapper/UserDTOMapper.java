package io.userauth.mapper;

import org.springframework.stereotype.Component;

import io.userauth.data.primary.models.Users;
import io.userauth.dto.user.UserDTO;


@Component
public class UserDTOMapper {

    public static UserDTO toDTO(Users user) {        
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        return dto;

    }
}
