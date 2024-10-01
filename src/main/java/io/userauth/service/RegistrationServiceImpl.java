package io.userauth.service;

import io.userauth.dto.auth.UserCreationForm;

public class RegistrationServiceImpl implements RegistrationService {

    private final UserService userService;


    public RegistrationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void register(UserCreationForm creationForm) {
        userService.createUser(creationForm);
        //TODO : send registration session token to cookies and send email verification, then redirect to email verification page
    }

}
