package io.userauth.service;

import io.userauth.dto.auth.UserCreationForm;

public interface RegistrationService {
    public void register(UserCreationForm creationForm);
}
