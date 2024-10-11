package io.userauth.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Setter;


@Setter
public class UsernameLoginForm implements ILoginForm {

    @NotBlank(message = "username cannot be blank")
    @Size(max = 20, message = "username too long")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$")
    String username;

    @NotBlank(message = "password cannot be blank")
    @Size(max = 255, message = "password too long")
    private String password;

    @Override
    public String getIdentifier() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

}
