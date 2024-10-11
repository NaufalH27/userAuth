package io.userauth.service;

import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.ILoginForm;

public interface AuthStrategy {
    AuthenticatedUser getAuthentication(ILoginForm loginForm);
}
