package io.userauth.dto.auth;

import org.hibernate.validator.constraints.UUID;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class AuthenticatedRefreshToken {
    private UUID token;
    private UUID userId;
    private Date issuedAt;
    private Date expiredAt;
}
