package com.jtudy.springaspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAspectApplication implements CommandLineRunner {

	@Autowired
	private SimpleService simpleService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAspectApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		
		simpleService.print();
	}
}
