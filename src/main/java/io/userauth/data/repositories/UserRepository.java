package io.userauth.data.repositories;

import io.userauth.data.entities.UserEntity;

public interface UserRepository {
    public UserEntity findById(int id);
    public void save(UserEntity entity);
    public UserEntity findByName(String name);
    public UserEntity findByEmail(String email);

}
