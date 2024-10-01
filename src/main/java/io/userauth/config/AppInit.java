package io.userauth.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.userauth.constant.RoleConstant;
import io.userauth.service.RoleService;

@Component
public class AppInit implements CommandLineRunner {

    private final RoleService roleService;

    public AppInit(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.createRole(RoleConstant.ADMIN);
        roleService.createRole(RoleConstant.USER);
        roleService.createRole(RoleConstant.OWNER);
        System.out.println("database initilizer successful");
    }
    
}
