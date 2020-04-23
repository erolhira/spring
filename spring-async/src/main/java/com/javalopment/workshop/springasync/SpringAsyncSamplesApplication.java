package com.javalopment.workshop.springasync;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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

	@Value("${pool.size}")
	private Integer poolSize;
	
	@Value("${pool.size.max}")
	private Integer maxPoolSize;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAsyncSamplesApplication.class, args);
	}
	
	@Bean("customTaskExecutor")
	public TaskExecutor setupCustomTaskExecutor() {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(poolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("customTaskExecutor");
		return executor;
	}
}
