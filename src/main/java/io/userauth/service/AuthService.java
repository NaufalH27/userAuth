package io.userauth.service;

import io.userauth.presentation.dto.auth.LoginEmailDTO;
import io.userauth.presentation.dto.auth.LoginUsernameDTO;
import io.userauth.presentation.dto.user.UserDTO;

public interface AuthService {
    public UserDTO authenticateByUsername(LoginUsernameDTO loginForm);
    public UserDTO authenticateByEmail(LoginEmailDTO loginForm);
    
}
