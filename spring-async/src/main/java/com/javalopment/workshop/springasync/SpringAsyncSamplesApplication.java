package com.javalopment.workshop.springasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Reading:
 * https://www.baeldung.com/spring-scheduled-tasks
 * https://www.baeldung.com/spring-async
 * https://www.baeldung.com/spring-task-scheduler
 * https://stackoverflow.com/questions/21993464/does-spring-scheduled-annotated-methods-runs-on-different-threads
 * 
 * spring.task.scheduling.pool.size=10
 *
 */
@SpringBootApplication
public class SpringAsyncSamplesApplication {	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAsyncSamplesApplication.class, args);
	}
		
}
