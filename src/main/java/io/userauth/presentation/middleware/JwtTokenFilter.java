package io.userauth.presentation.middleware;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        if(path.startsWith("/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = CookieUtils.getCookieValue(request, "token").getValue();

        if (jwtToken == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("missing JWT token");
            return;
        }
        String subject = jwtHelper.getSubject(jwtToken);
        
        if (subject != null) {
        
        }


        filterChain.doFilter(request, response);
    }

   
 
}
