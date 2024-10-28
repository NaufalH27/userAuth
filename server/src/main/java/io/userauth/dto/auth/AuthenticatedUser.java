package io.userauth.dto.auth;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedUser {
    private UUID id;
    private String username;
    private String email;
    private List<String> role;

}
