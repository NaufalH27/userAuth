package io.userauth.data.repositories;

import java.util.List;

import io.userauth.models.entities.User;

public interface UserRepository {
    public User findById(int id);
    public void createUser(User entity);
    public User findByName(String name);
    public User findByEmail(String email);
    public List<User> getAllUsers();
    public void updateEmail(int id, String newEmail);
    public void deleteUser(int id);

}
