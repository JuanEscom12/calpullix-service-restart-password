package com.calpullix.service.restart.password;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@ComponentScan(basePackages = "com.calpullix")
@EnableWebFlux
@EnableCircuitBreaker
@EnableAutoConfiguration( exclude = RabbitAutoConfiguration.class) 
@EnableAsync
@EnableDiscoveryClient
public class CalpullixServiceRestartPasswordApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalpullixServiceRestartPasswordApplication.class, args);
	}

}
