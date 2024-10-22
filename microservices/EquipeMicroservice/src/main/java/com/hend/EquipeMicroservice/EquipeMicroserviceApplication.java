package com.hend.EquipeMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EquipeMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipeMicroserviceApplication.class, args);
	}

}
