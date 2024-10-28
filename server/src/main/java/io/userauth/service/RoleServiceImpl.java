package io.userauth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.userauth.data.repositories.RoleRepository;
import io.userauth.models.Roles;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void createRole(String roleName) {
        Roles role = new Roles();
        role.setName(roleName);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(long roleId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public void renameRole(long roleId, String newRoleName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
     @Transactional(readOnly = true)
    public void getAllRoles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
