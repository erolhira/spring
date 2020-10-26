package com.javalopment.workshop.springasync;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@EnableScheduling
public class ConsumerService extends ConsumerServiceBase {

	
	@Autowired TaskScheduler customTaskScheduler;
	
	@Override
	public void consumeSingle() {
		
		ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) customTaskExecutor;
		Integer queueSize = executor.getThreadPoolExecutor().getQueue().size();		
		System.out.println(new Date() + " -- " + Thread.currentThread().getName() + " -- queueSize: " + queueSize);
	}

	
	@Override
	public void consumeMultiple(String param) {
		
		ThreadPoolTaskScheduler executor = (ThreadPoolTaskScheduler) customTaskScheduler;
		Integer activeThreadCount = executor.getActiveCount();
		System.out.println(param + " -- " + new Date() + " -- " + Thread.currentThread().getName() + " -- activeThreadCount: " + activeThreadCount);
	}
}
