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
import io.userauth.service.AuthStrategy.AuthStrategy;
import io.userauth.service.AuthStrategy.AuthStrategyFactory;
import io.userauth.service.authService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTHelper jwtHelper;
    private final AuthService authService;
    private final AuthStrategyFactory AuthStrategyFactory;

    @Autowired
    public SecurityConfig(JWTHelper jwtHelper, AuthService authService, AuthStrategyFactory AuthStrategyFactory) {
        this.jwtHelper = jwtHelper;
        this.authService = authService;
        this.AuthStrategyFactory = AuthStrategyFactory;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth 
                .anyRequest().authenticated() 
            ).addFilterBefore(new JwtTokenFilter(this.jwtHelper, 
                                                this.authService, 
                                                this.AuthStrategyFactory
                                                ), BasicAuthenticationFilter.class);
            return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/login/**", "/logout", "/signup"); 
    }


}

