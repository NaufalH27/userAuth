package io.userauth.dto.auth;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshTokenForm implements ILoginForm{
    UUID tokenId;
}
