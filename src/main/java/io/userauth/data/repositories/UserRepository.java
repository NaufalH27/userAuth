package io.userauth.data.repositories;

import java.util.List;

import io.userauth.models.Users;

public interface UserRepository {
    public Users findById(int id);
    public void createUser(Users entity);
    public Users findByName(String name);
    public Users findByEmail(String email);
    public List<Users> getAllUsers();
    public void updateEmail(int id, String newEmail);
    public void deleteUser(int id);

}
