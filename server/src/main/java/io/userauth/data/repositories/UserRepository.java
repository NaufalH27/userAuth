package io.userauth.data.repositories;

import java.util.List;
import java.util.UUID;

import io.userauth.models.Users;

public interface UserRepository {
    public Users findById(UUID id);
    public void save(Users entity);
    public Users findByName(String name);
    public Users findByEmail(String email);
    public List<Users> getAllUsers();
    public void deleteUser(UUID id);

}
