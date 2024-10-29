package io.userauth.presentation.exception;

public enum AuthErrorCode {
    USER_BANNED,
    USER_INACTIVE,
    USER_PENDING_FOR_DELETION,
    USER_NOT_FOUND,
    INVALID_PASSWORD,
    UNSUPPORTED_AUTH,
    UNKNOWN_STATUS
}
