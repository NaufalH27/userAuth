package io.userauth.models.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCreationDTO{
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Username minimum is 3 characters")
    @Size(max = 25, message = "Username maximum is 25 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Illegal symbol or namespace detected. Use \".\", \"_\", \"-\" instead")
    private String name;


    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email too long")
    private String email;


    @NotBlank(message = "Password cannot be blank")
    @Size(max = 255, message = "Password too long")
    private String password;
    

    @NotBlank(message = "You need to repeat your password")
    private String repeatPassword;



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = (email == null || email.isEmpty() || email.isBlank()) ? null : email;
    }
 
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return this.repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }


}
