package io.userauth.presentation.exception;

import io.userauth.constant.UserStatus;

public enum AuthErrorCode {
    USER_BANNED,
    USER_INACTIVE,
    USER_PENDING_FOR_DELETION,
    USER_NOT_FOUND,
    INVALID_PASSWORD,
    UNSUPPORTED_AUTH,
    UNKNOWN_STATUS;

    public static AuthErrorCode fromUserStatus(UserStatus userStatus) {
        return switch(userStatus) {
            case PENDING_FOR_DELETION -> USER_PENDING_FOR_DELETION;
            case INACTIVE ->  USER_INACTIVE;
            case BANNED -> USER_BANNED;
            default ->UNKNOWN_STATUS;
        };
    }
}
