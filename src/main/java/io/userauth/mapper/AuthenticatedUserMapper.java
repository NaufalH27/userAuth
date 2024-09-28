package io.userauth.mapper;


import java.util.stream.Collectors;

import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.models.Roles;
import io.userauth.models.Users;

public class AuthenticatedUserMapper {

    public static AuthenticatedUser toDTO(Users user) {        
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setId(user.getId());
        authenticatedUser.setUsername(user.getUsername());
        authenticatedUser.setEmail(user.getEmail());
        authenticatedUser.setRole(user.getRoles()
                                    .stream()
                                    .map(Roles::getName)
                                    .collect(Collectors.toList()));

        return authenticatedUser;

    }
}
