package com.example.sampleaxondiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SampleAxonDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleAxonDiscoveryApplication.class, args);
	}
}
