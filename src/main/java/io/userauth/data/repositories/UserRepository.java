package io.userauth.data.repositories;

import java.util.List;

import io.userauth.data.entities.UserEntity;

public interface UserRepository {
    public UserEntity findById(int id);
    public void createUser(UserEntity entity);
    public UserEntity findByName(String name);
    public UserEntity findByEmail(String email);
    public List<UserEntity> getAllUsers();
    public void updateEmail(int id, String newEmail);
    public void deleteUser(int id);

}
