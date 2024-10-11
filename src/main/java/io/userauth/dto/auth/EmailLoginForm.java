package io.userauth.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Setter;


@Setter
public class EmailLoginForm implements ILoginForm {
    @NotBlank(message = "email cant be blank")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "email input are too long")
    String email;

    @NotBlank(message = "password cannot be blank")
    @Size(max = 255, message = "password too long")
    private String password;

    @Override
    public String getIdentifier() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    
}
