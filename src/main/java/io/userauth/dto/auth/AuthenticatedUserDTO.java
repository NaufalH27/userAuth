package io.userauth.dto.auth;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedUserDTO {
    private Long id;
    private String name;
    private String email;
    private Set<String> role;

}
