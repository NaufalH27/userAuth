package io.userauth.presentation.exception;

import io.userauth.constant.TokenStatus;

public enum RefreshTokenErrorCode {
    TOKEN_NONEXISTENCE,
    TOKEN_EXPIRED,
    TOKEN_USED,
    TOKEN_REVOKED,
    TOKEN_UNKNOWN_STATUS;

    public static RefreshTokenErrorCode fromTokenStatus(TokenStatus tokenStatus) {
        return switch(tokenStatus) {
            case EXPIRED ->  TOKEN_EXPIRED;
            case REVOKED -> TOKEN_REVOKED;
            case USED -> TOKEN_USED;
            default -> TOKEN_UNKNOWN_STATUS;
        };
    }
}
