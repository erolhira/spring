package com.javalopment.workshop.springasync;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/*
 * if corePoolSize is reached, and queueCapacity is full; new executor threads will be created unless the maxPoolSize is reached.
 * if maxPoolSize is reached, and queueCapacity is full; new submitted tasks will be rejected with an exception.
 */
@Configuration
public class TaskExecutorConfig {

	/*
	 * Default is 1
	 */
	@Value("${pool.size}")
	private Integer poolSize;
	
	/*
	 * Default is Integer.MAX_VALUE	 
	 */
	@Value("${pool.size.max}")
	private Integer maxPoolSize;
	
	/*
	 * Default is Integer.MAX_VALUE	 
	 */
	@Value("${pool.queue.capacity}")
	private Integer queueCapacity;
	
	@Bean("customTaskExecutor")
	public TaskExecutor setupCustomTaskExecutor() {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(poolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("customTaskExecutor");
		return executor;
	}
	
	@Bean("customTaskScheduler")
	public TaskScheduler setupCustomTaskScheduler() {
		
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();		 
		scheduler.setPoolSize(poolSize);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		scheduler.setThreadNamePrefix("customTaskScheduler");
		return scheduler;
	}
}
