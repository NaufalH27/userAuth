package io.userauth.models.dto.user;

public class UserDTO{
    private Long id;
    private String name;
    private String email;


    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
       this.email = email;
    }

}
