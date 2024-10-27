package io.userauth.service.AuthStrategy;

import io.userauth.dto.auth.AuthenticatedUser;
import io.userauth.dto.auth.ILoginForm;

public interface AuthStrategy<T extends ILoginForm> {
    AuthenticatedUser getAuthentication(T loginForm);
}
