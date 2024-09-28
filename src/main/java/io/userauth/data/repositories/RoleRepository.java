package io.userauth.data.repositories;

import java.util.List;

import io.userauth.models.Roles;

public interface RoleRepository {
    public List<Roles> getAllRoles();
    public Roles getAdminRole();
    public Roles getUserRole();
    public Roles getOwnerRole();
    public void initialRoles();
}
