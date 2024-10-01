package io.userauth.service;

import org.springframework.stereotype.Service;

import io.userauth.data.repositories.RoleRepository;
import io.userauth.models.Roles;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRole(String roleName) {
        Roles role = new Roles();
        role.setName(roleName);
        roleRepository.createRole(role);
    }

    @Override
    public void deleteRole(long roleId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void renameRole(long roleId, String newRoleName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void getAllRoles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
