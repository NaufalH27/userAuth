package io.userauth.mapper;

import io.userauth.dto.auth.AuthenticatedUserDTO;
import io.userauth.models.UserEntity;

public class AuthenticatedUserDTOMapper {

    public static AuthenticatedUserDTO toDTO(UserEntity entity){
        if (entity == null){
            return null;
        }
        
        AuthenticatedUserDTO dto = new AuthenticatedUserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());

        return dto;

    }
}
