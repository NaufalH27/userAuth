package io.userauth.presentation.middleware;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import io.userauth.constant.CookieName;
import io.userauth.dto.auth.CustomUserDetails;
import io.userauth.mapper.RoleAuthorityMapper;
import io.userauth.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JWTHelper jwtHelper;

    public JwtTokenFilter(JWTHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
        ) throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/signup") || path.startsWith("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        Cookie[] cookie = request.getCookies();
        String accessToken = CookieUtils.getCookieValue(cookie, CookieName.ACCESS_TOKEN);

        if (accessToken == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or missing JWT token");
            return;
        }

        try {
                                    
            UsernamePasswordAuthenticationToken authentication = convertAccessTokenToUserDetails(accessToken);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));    
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("expired JWT Token");
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid JWT Token");
        } catch (IllegalArgumentException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("illegal jwt Token");
        }
    } 


    private UsernamePasswordAuthenticationToken convertAccessTokenToUserDetails(String accessToken) {
        String subject = jwtHelper.getSubject(accessToken);
        UUID userId = jwtHelper.getId(accessToken);
        List<GrantedAuthority> userRoles = RoleAuthorityMapper.toAuthorities(jwtHelper.getRoleList(accessToken));
        UserDetails user = new CustomUserDetails(subject, userId,userRoles);
                                
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

 
}
