package com.citi.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmsTestApplication {

	public static void main(String[] args) {
		System.out.println("Started app ....");
		SpringApplication.run(OmsTestApplication.class, args);
	}

}