package io.userauth.mapper;

import io.userauth.constant.UserStatus;
import io.userauth.presentation.exception.AuthErrorCode;

public class AuthErrorCodeMapper {
    public static AuthErrorCode fromUserStatus(UserStatus userStatus) {
        return switch(userStatus) {
            case PENDING_FOR_DELETION -> AuthErrorCode.USER_PENDING_FOR_DELETION;
            case INACTIVE ->  AuthErrorCode.USER_INACTIVE;
            case BANNED -> AuthErrorCode.USER_BANNED;
            default -> unmappedStatus(userStatus);
        };
    }
  
    private static AuthErrorCode unmappedStatus(UserStatus status) {
        //logger for unmapped status
        return AuthErrorCode.UNKNOWN_STATUS;
    }
}
