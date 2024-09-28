package io.userauth.service;

import java.util.UUID;

import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.models.Users;

public interface RoleService {
    public void createRole(String roleName);
    public void deleteRole(long roleId);
    public void assignRole(UUID userId, long roleId);
}
