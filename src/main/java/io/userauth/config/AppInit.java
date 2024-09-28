package io.userauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.userauth.data.repositories.RoleRepository;

@Component
public class AppInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public AppInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("database initilizer successful");
        roleRepository.initialRoles();
    }
    
}
