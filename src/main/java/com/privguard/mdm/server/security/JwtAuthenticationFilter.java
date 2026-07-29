package com.privguard.mdm.server.security;

import com.privguard.mdm.server.account_tokens.AccountTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AccountTokenService tokenService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            AccountTokenService tokenService) {

        this.jwtService = jwtService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name + " = " + request.getHeader(name));
        }

        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ") || authorization.length() < 30) {

            System.out.println("No bearer token");
            filterChain.doFilter(request, response);
            return;
        }

        try {

            String jwt = authorization.substring(7);
            String jti = jwtService.getJti(jwt);

            AuthenticatedAccount principal = tokenService.validateToken(jti);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    principal,
                    null,
                    Collections.emptyList()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch(Exception _e) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
