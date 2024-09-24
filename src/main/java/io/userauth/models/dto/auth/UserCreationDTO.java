package io.userauth.models.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCreationDTO{
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Username minimum is 3 characters")
    @Size(max = 25, message = "Username maximum is 25 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Illegal symbol or namespace detected. Use \".\", \"_\", \"-\" instead")
    private String name;



    @NotBlank(message = "Password cannot be blank")
    @Size(max = 255, message = "Password too long")
    private String password;
    



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
