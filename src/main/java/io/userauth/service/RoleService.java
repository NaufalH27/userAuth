package io.userauth.service;

public interface RoleService {
    public void createRole(String roleName);
    public void deleteRole(long roleId);
    public void renameRole(long roleId, String newRoleName);
    public void getAllRoles();
}
