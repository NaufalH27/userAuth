package io.userauth.presentation.middleware;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.userauth.common.CookieUtils;
import io.userauth.common.JWTHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
        String path = request.getServletPath();

        if(path.startsWith("/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = CookieUtils.getCookieValue(request, "token");

        if (jwtToken == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "missing JWT token");
        }

        try {
      



        } catch (ExpiredJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token is expired");
        } catch (JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
        }

        


        filterChain.doFilter(request, response);
    }

   
 
}
