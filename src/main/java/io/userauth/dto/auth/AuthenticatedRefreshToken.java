package io.userauth.dto.auth;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticatedRefreshToken {
    private UUID token;
    private UUID userId;
    private Date issuedAt;
    private Date expiredAt;
}
