package com.Authentication.Auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.Authentication.Auth.Entities.Roles;
import com.Authentication.Auth.Entities.Token;
import com.Authentication.Auth.repositories.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses ={UserRepository.class, Roles.class, Token.class} )
@EnableDiscoveryClient
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
