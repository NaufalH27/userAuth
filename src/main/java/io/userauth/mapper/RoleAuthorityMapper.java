package io.userauth.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class RoleAuthorityMapper {

    private static final String ROLE_SUFFIX = "ROLE_";

    public static List<GrantedAuthority> toAuthorities(List<String> roleList){
        List<GrantedAuthority> authorities = new ArrayList<>();
        roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(ROLE_SUFFIX + role));
            });
        return authorities;
    }
}
