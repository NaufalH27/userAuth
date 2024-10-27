package io.userauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.userauth.common.JWTHelper;
import io.userauth.presentation.middleware.JwtTokenFilter;
import io.userauth.service.AuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTHelper jwtHelper;
    private final AuthService authService;

    @Autowired
    public SecurityConfig(JWTHelper jwtHelper, AuthService authService) {
        this.jwtHelper = jwtHelper;
        this.authService = authService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth 
                .anyRequest().authenticated() 
            ).addFilterBefore(new JwtTokenFilter(this.jwtHelper, this.authService), BasicAuthenticationFilter.class);
            return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/login/**", "/signup"); 
    }


}

