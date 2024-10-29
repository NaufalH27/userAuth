package io.userauth.presentation.exception;

import lombok.Getter;

@Getter
public class RefreshTokenException extends Exception {

    private final RefreshTokenErrorCode errorCode;
 
    public RefreshTokenException(RefreshTokenErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
}
