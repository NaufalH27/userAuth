package io.userauth.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UsernameLoginForm {
    @NotBlank(message = "username cannot be blank")
    @Size(max = 20, message = "username too long")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$")
    String username;

    @NotBlank(message = "password cannot be blank")
    @Size(max = 255, message = "password too long")
    private String password;


}
