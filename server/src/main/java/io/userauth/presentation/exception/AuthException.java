package io.userauth.presentation.exception;

import lombok.Getter;

@Getter
public class AuthException extends Exception {
 
    private final AuthErrorCode errorCode;
 
    public AuthException(AuthErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    
}
