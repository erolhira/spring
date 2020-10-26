package com.javalopment.workshop.springasync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class SpringAsyncSamplesApplication implements CommandLineRunner {	
	
	@Autowired ConsumerService consumerService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAsyncSamplesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		List<Future<Void>> futureList = new ArrayList<>();
//        for (int groupIndex = 0; groupIndex < 15; groupIndex++) {
//            Future<Void> future = consumerService.consume();
//            futureList.add(future);
//        }
//        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).get();
	}
		
}
