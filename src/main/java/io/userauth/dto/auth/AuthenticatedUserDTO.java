package io.userauth.dto.auth;

import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedUserDTO {
    private UUID id;
    private String username;
    private String email;
    private Set<String> role;

}
