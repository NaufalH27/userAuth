package io.userauth.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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

 
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
