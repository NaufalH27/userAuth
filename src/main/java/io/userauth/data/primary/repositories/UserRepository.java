package io.userauth.data.primary.repositories;

import java.util.List;
import java.util.UUID;

import io.userauth.data.primary.models.Users;

public interface UserRepository {
    public Users findById(UUID id);
    public void createUser(Users entity);
    public Users findByName(String name);
    public Users findByEmail(String email);
    public List<Users> getAllUsers();
    public void deleteUser(UUID id);

}
