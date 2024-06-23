package com.Spring_IA_2.PomodoroWebsite.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurations {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/api/getSongsList").allowedOrigins("*");
                registry.addMapping("/api/downloadSong/**").allowedOrigins("*");
                registry.addMapping("/api/uploadSongs").allowedOrigins("*");
                registry.addMapping("/api/deleteSongs/**").allowedOrigins("*");
            }
        };
    }
}
