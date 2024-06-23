package com.UserMicroService.TrackUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TrackUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackUserApplication.class, args);
	}

}
