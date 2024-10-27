package io.userauth.service.AuthStrategy;

import io.userauth.dto.auth.AuthenticatedUser;

public interface AuthStrategy<T> {
    AuthenticatedUser getAuthentication(T loginForm);
}
