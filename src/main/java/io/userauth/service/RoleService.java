package io.userauth.service;

import java.util.UUID;

public interface RoleService {
    public void createRole(String roleName);
    public void deleteRole(long roleId);
    public void assignRole(UUID userId, long roleId);
}
