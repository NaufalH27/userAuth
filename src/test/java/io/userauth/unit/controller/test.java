package io.userauth.unit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.userauth.presentation.controllers.AuthController;
import io.userauth.presentation.controllers.UserController;
import io.userauth.presentation.dto.auth.LoginUsernameDTO;

@ActiveProfiles("test")
@SpringBootTest
public class test {

    @Autowired
    private UserController userController;

    @Autowired
    private AuthController authController;

   



    @Test
    public void authenticateByName_testValid(){
        LoginUsernameDTO loginForm = new LoginUsernameDTO();
        loginForm.setUsername("momoiResing");
        loginForm.setPassword("momoi123");    
    }
}
