package io.userauth.service;

import java.util.UUID;

import io.userauth.dto.auth.UserCreationForm;
import io.userauth.dto.user.UserDTO;

public interface UserService {
    public void createUser(UserCreationForm creationForm);
    public UserDTO getUserById(UUID id);
    public UserDTO getUserByEmail(String email);
    public UserDTO getUserByName(String name);
    public void updateEmail(UUID id, String newEmail);
    public void deleteUser(UUID id);
}
