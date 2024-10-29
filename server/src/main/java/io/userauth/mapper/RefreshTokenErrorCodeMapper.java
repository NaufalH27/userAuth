package io.userauth.mapper;

import io.userauth.constant.TokenStatus;
import io.userauth.presentation.exception.RefreshTokenErrorCode;

public class RefreshTokenErrorCodeMapper {
    public static RefreshTokenErrorCode fromTokenStatus(TokenStatus tokenStatus) {
        return switch(tokenStatus) {
            case EXPIRED ->  RefreshTokenErrorCode.TOKEN_EXPIRED;
            case REVOKED -> RefreshTokenErrorCode.TOKEN_REVOKED;
            case USED -> RefreshTokenErrorCode.TOKEN_USED;
            default -> unmappedStatus(tokenStatus);
        };
    }
    
    private static RefreshTokenErrorCode unmappedStatus(TokenStatus status) {
        //logger for unmapped status
        return RefreshTokenErrorCode.TOKEN_UNKNOWN_STATUS;
    }
}
