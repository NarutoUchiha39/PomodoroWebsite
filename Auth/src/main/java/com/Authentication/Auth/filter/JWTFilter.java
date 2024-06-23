package com.Authentication.Auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Authentication.Auth.Entities.UserInfo;
import com.Authentication.Auth.Services.JWTService;
import com.Authentication.Auth.classes.UserDetailService;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailService userDetails;

    @Autowired
    JWTFilter(JWTService jwtService, UserDetailService userDetails) {
        this.jwtService = jwtService;
        this.userDetails = userDetails;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        String username = jwtService.extractEmail(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserInfo userDetails1 = userDetails.loadUserByUsername(username);
            if (jwtService.isValid(token, userDetails1)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails1, null, userDetails1.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }
        filterChain.doFilter(request, response);
    }
}
