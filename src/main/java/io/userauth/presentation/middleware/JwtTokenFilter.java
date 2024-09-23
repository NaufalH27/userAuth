package io.userauth.presentation.middleware;

// import java.io.IOException;

// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.Cookie;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtTokenFilter extends OncePerRequestFilter {

//     private boolean validateToken(String token) {
//         return token != null && token.equals("validToken"); 
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//         Cookie[] cookies = request.getCookies();
//         String jwtToken = null;

//         if (cookies != null) {
//             for (Cookie cookie : cookies) {
//                 if ("token".equals(cookie.getName())) {
//                     jwtToken = cookie.getValue();
//                     break;
//                 }
//             }
//         }

//         if (jwtToken == null || !validateToken(jwtToken)) {
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             response.getWriter().write("Invalid or missing JWT token");
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }
