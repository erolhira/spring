package org.jtudy.springcomponents;

import org.jtudy.springcomponents.bookmanagement.BookManagementConfiguration;
import org.jtudy.springcomponents.customermanagement.CustomerManagementConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({BookManagementConfiguration.class, CustomerManagementConfiguration.class })
@EnableAutoConfiguration
public class SpringComponentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringComponentsApplication.class, args);
	}

}
