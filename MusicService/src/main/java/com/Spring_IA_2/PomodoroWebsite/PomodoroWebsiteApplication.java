package com.Spring_IA_2.PomodoroWebsite;

import com.Spring_IA_2.PomodoroWebsite.controllers.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = {com.Spring_IA_2.PomodoroWebsite.classes.runner.RunnerImpl.class, Api.class,com.Spring_IA_2.PomodoroWebsite.configuration.Config.class,com.Spring_IA_2.PomodoroWebsite.services.SongUploadService.class})
public class PomodoroWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PomodoroWebsiteApplication.class, args);
	}

}
