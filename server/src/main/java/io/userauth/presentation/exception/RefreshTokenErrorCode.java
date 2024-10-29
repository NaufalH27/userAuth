package io.userauth.presentation.exception;

public enum RefreshTokenErrorCode {
    TOKEN_NONEXISTENCE,
    TOKEN_EXPIRED,
    TOKEN_USED,
    TOKEN_REVOKED,
    TOKEN_UNKNOWN_STATUS
}
