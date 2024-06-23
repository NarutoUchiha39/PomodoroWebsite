package com.ApiGateWay.GateWay.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routers {



    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("Auth",r->r.path("/api/getSongsList")
                        .and().method("GET").and()
                        .uri("http://localhost:8080")
                )
                .route("MusicService",r->r.path("/api/uploadSongs")
                        .and().method("POST")
                        .and().uri("http://localhost:8081")
                ).

                route("MusicService",r->r.path("/api/deleteSongs/**")
                .and().method("GET")
                .and().uri("http://localhost:8081")
                )

                .route("MusicService",r->r.path("/api/editSongs/**")
                        .and().method("POST")
                        .and().uri("http://localhost:8081")
                )

                .route("Auth",r->r.path("/api/downloadSong/**")
                        .and().method("GET")
                        .and().uri("http://localhost:8080")
                )
                .route(
                        "Auth",r->r.path("/api/getTimeStudied")
                                .and().method("GET")
                                .and().uri("http://localhost:8080")
                )
                .route(
                "Auth",r->r.path("/login")
                        .and().method("POST")
                        .and().uri("http://localhost:8080")
                ).route(
                        "Auth",r->r.path("/register")
                                .and().method("POST")
                                .and().uri("http://localhost:8080")
                ).route(
                        "Auth",r->r.path("/api/updateTimer/**")
                                .and().method("GET")
                                .and().uri("http://localhost:8080")
                )
                .build();
    }
}
