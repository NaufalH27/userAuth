package io.userauth.common;

import io.userauth.constant.UserStatus;

public class UserUtils {
    public void verifyUserStatus(UserStatus userStatus) {
        switch(userStatus) {
            case ACTIVE -> {};
            case PENDING_FOR_DELETION -> throw new IllegalArgumentException("account is pending to be deleted try to recover?");
            case INACTIVE -> throw new IllegalArgumentException("account has been deactivated, try to activate it back?");
            case BANNED -> throw new IllegalArgumentException("user is banned");
            default -> throw new IllegalArgumentException("internal server error");
        };
    }
}
