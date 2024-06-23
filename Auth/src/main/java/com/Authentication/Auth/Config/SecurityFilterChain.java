package com.Authentication.Auth.Config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Authentication.Auth.classes.UserDetailService;
import com.Authentication.Auth.filter.JWTFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterChain {
    private final UserDetailService userDetailService;
    private final JWTFilter jwtFilter;

    CustomAccessEntryPoint CustomAccessEntryPoint;

    @Autowired
    SecurityFilterChain(UserDetailService userDetailService,
                        JWTFilter jwtFilter,
                        CustomAccessEntryPoint authenticationConfiguration){
        this.userDetailService = userDetailService;
        this.jwtFilter = jwtFilter;
        this.CustomAccessEntryPoint = authenticationConfiguration;
    }

    @Bean
    org.springframework.security.web.SecurityFilterChain securityFilterChains(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(CustomAccessEntryPoint)
                )

                .authorizeHttpRequests(
                        req -> req.requestMatchers("/login/**", "/register/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userDetailService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();


    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
