package io.userauth.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDTO{
    
    @NotBlank(message = "email cannot be blank")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email too long")
    private String email;

    @NotBlank(message = "username cannot be blank")
    @Size(min = 3, message = "Username minimum is 3 characters")
    @Size(max = 25, message = "Username maximum is 25 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Illegal symbol or namespace detected. Use \".\", \"_\", \"-\" instead")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(max = 255, message = "Password too long")
    private String password;


}
