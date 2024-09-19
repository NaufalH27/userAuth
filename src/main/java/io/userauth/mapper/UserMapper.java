package io.userauth.mapper;

import org.springframework.stereotype.Component;

import io.userauth.data.entities.UserEntity;
import io.userauth.presentation.dto.user.UserDTO;



@Component
public class UserMapper implements Mapper<UserEntity, UserDTO > {

    @Override
    public UserEntity toEntity(UserDTO dto){

        if (dto == null){
            return null;
        }

        UserEntity entity = new UserEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        return entity;
    }

    @Override
    public UserDTO toDTO(UserEntity entity){

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
