package io.userauth.mapper;

import java.util.stream.Collectors;

import io.userauth.dto.auth.AuthenticatedUserDTO;
import io.userauth.models.Role;
import io.userauth.models.UserEntity;

public class AuthenticatedUserDTOMapper {

    public static AuthenticatedUserDTO toDTO(UserEntity user){        
        AuthenticatedUserDTO dto = new AuthenticatedUserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()));

        return dto;

    }
}
