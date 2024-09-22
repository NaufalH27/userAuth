package io.userauth.models.dto.auth;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthResult {
    private Map<String, Object> claims;
    private UserDetails userDetails;


    public Map<String,Object> getClaims() {
        return this.claims;
    }

    public void setClaims(Map<String,Object> claims) {
        this.claims = claims;
    }

    public UserDetails getUserDetails() {
        return this.userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}
