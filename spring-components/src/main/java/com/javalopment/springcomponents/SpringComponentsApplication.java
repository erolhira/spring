package com.javalopment.springcomponents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.javalopment.springcomponents.bookmanagement.BookManagementConfiguration;
import com.javalopment.springcomponents.customermanagement.CustomerManagementConfiguration;

@Import({BookManagementConfiguration.class, CustomerManagementConfiguration.class })
@EnableAutoConfiguration
public class SpringComponentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringComponentsApplication.class, args);
	}

}
