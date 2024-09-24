package io.userauth.models.dto.auth;

import java.util.UUID;

public class emailVerifyDTO {
    private UUID sessionToken;
    private String verificationCode;

    public UUID getSessionToken() {
        return this.sessionToken;
    }

    public void setSessionToken(UUID sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

}
