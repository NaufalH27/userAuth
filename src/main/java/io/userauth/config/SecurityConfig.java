package io.userauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.userauth.presentation.middleware.JwtTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .requestMatchers("/auth/**").permitAll() 
                .requestMatchers("/users/**").permitAll() 
                .anyRequest().authenticated() 
            ).addFilterBefore(new JwtTokenFilter(), BasicAuthenticationFilter.class);
            return http.build();
    }


}

