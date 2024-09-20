package io.userauth.service;

import io.userauth.presentation.dto.user.UserCreationDTO;
import io.userauth.presentation.dto.user.UserDTO;

public interface UserService {
    public UserDTO getUserById(int id);
    public UserDTO getUserByEmail(String email);
    public UserDTO getUserByName(String name);
    public void createUser(UserCreationDTO creationForm);
    public void updateEmail(int id, String newEmail);
    public void deleteUser(int id);
}
