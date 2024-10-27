package io.userauth.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class EmailLoginForm implements ILoginForm {
    @NotBlank(message = "email cant be blank")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "email input are too long")
    String email;

    @NotBlank(message = "password cannot be blank")
    @Size(max = 255, message = "password too long")
    private String password;
    
}
