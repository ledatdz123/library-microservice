package com.capgemini.datle.bookservicev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Bookservicev2Application {

	public static void main(String[] args) {
		SpringApplication.run(Bookservicev2Application.class, args);
	}

}
