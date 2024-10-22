package com.hend.ProjetMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjetMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetMicroserviceApplication.class, args);
	}

}
