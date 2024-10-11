package io.userauth.data.repositories;

import java.util.List;

import io.userauth.models.Roles;

public interface RoleRepository {
    public Roles findRoleById(long id);
    public List<Roles> getAllRoles();
    public Roles findRoleByName(String roleName);
    public void createRole(Roles role);
}
