package io.userauth.dto.auth;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final UUID Id;  
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, UUID Id, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.Id = Id;
        this.authorities = authorities;
    }

     @Override
     public String getUsername() {
         return this.username;
     }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    public UUID getId() {
        return this.Id;
    }
    
}
