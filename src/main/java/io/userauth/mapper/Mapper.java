package io.userauth.mapper;

public interface Mapper<Entity, DTO>{
    public Entity toEntity(DTO dto);
    public DTO toDTO(Entity entity);
}
