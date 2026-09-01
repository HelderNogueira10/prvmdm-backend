package com.privguard.mdm.server.security;

import com.privguard.mdm.server.global.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter _jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = _jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity _httpSec) throws Exception {

        _httpSec
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth.requestMatchers(
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/v3/api-docs/**",
                "/v3/api-docs",
                "/api/health",
                "/api/auth/user",
                "/api/auth/agent",
                "/api/users/create",
                "/api/provisioning/**",
                "/api/auth/device_account",
                "/api/auth/user_account",
                "/api/auth/service_account",
                "/api/auth/api_account",
                "/api/apps/files/get/**",
                "/api/users/create",
                "/api/commands/add", Constants.API_PREFIX + "/enrollment/validateToken"
            ).permitAll()
            .anyRequest().authenticated()
        ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return _httpSec.build();
    }
}

