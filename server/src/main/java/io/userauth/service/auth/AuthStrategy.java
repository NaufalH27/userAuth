package io.userauth.service.auth;

import io.userauth.dto.auth.AuthForm;
import io.userauth.dto.auth.AuthenticatedUser;

//can only be accesed in the auth directory
public interface AuthStrategy {
    public AuthenticatedUser getAuthentication(AuthForm loginForm);
}
