package com.capgemini.datle.borrowingservicev2;

import com.capgemini.datle.borrowingservicev2.command.api.events.BorrowingEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class Borrowingservicev2Application {

	public static void main(String[] args) {
		SpringApplication.run(Borrowingservicev2Application.class, args);
	}

}
