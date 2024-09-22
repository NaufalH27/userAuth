package io.userauth.models.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginUsernameDTO {

    @NotBlank(message = "username cannot be blank")
    @Size(max = 255, message = "username too long")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$")
    String username;

    @NotBlank(message = "password cannot be blank")
    @Size(max = 255, message = "password too long")
    private String password;

    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
