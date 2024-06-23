package com.Gateway.SpringGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringGatewayApplication {

	private spriing spriing = new spriing();

	private void access(){
		this.spriing.getName();
	}
	public static void main(String[] args)
	{

		SpringApplication.run(SpringGatewayApplication.class, args);
	}

}
