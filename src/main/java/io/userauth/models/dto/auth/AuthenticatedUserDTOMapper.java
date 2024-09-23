package io.userauth.models.dto.auth;

import io.userauth.models.entities.UserEntity;

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
