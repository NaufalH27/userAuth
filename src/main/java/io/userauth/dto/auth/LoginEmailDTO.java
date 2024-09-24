package io.userauth.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginEmailDTO {
    @NotBlank(message = "email cant be blank")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "email input are too long")
    String email;

    @NotBlank(message = "password cannot be blank")
    @Size(max = 255, message = "password too long")
    private String password;

    
    public String getemail() {
        return this.email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
