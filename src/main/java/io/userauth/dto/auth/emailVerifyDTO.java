package io.userauth.dto.auth;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class emailVerifyDTO {
    private UUID sessionToken;
    private String verificationCode;

}
