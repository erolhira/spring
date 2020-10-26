package com.javalopment.workshop.springasync;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;

/*
 * @EnableAsync annotation enables Spring to run @Async methods in a background thread pool.
 */
public abstract class ConsumerServiceBase {	
	
	@Autowired Executor customTaskExecutor;
	
	//@Value("#{1000 / (${consumer.tps.max} / ${pool.size.max})}")
	private Long minFrameInMillis = 20L;
	
	public abstract void consumeSingle();
	public abstract void consumeMultiple(String param);
	
	@Async("customTaskExecutor")	
    public CompletableFuture<Void> consume() {
        
		long threadId = Thread.currentThread().getId();
		
		CompletableFuture<Void> asyncResult = new CompletableFuture<>();
		
		System.out.println("Thread-" + threadId + " begins -- " + new Date());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stopWatch.stop();
		System.out.println("Thread-" + threadId + " ends in " + stopWatch.getTotalTimeMillis() + "ms -- " + new Date());
		
		asyncResult.complete(null);
		return asyncResult;
    }
	
	@Async("customTaskExecutor")    
	//@Scheduled(fixedDelay = 1000, initialDelay = 1000000000L)
	//@Scheduled(fixedRateString = "#{1000 / ${consumer.tps}}")	
    public void consumeOneAtATime() {
        
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {			
			consumeSingle();
		} finally {
			stopWatch.stop();
			sleep(stopWatch.getLastTaskTimeMillis());
		}
    }

	@Async("customTaskScheduler")
	//@Scheduled(fixedRate = 1)
	//@Scheduled(fixedDelay = 1000)
    public void consumeMultipleAtATime() {
        
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {			
			consumeMultiple("consumeMultipleAtATime");
		} finally {
			stopWatch.stop();
			sleep(stopWatch.getLastTaskTimeMillis());
		}
    }
	
	@Async("customTaskScheduler")	
	@Scheduled(fixedDelay = 1000)
    public void consumeFixedDelay() {
        
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {			
			consumeMultiple("consumeFixedDelay");
		} finally {
			stopWatch.stop();			
		}
    }
	
	@Async("customTaskScheduler")	
	@Scheduled(fixedRate = 100)
    public void consumeFixedRate() {
        
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {			
			consumeMultiple("consumeFixedRate");
		} finally {
			stopWatch.stop();
			long l = 19;
			for(int i = 1; i< 999999999; i++) {
				l = l * i;
			}
		}
    }
	
	private void sleep(long durationOfTask) {
		try {
			if(minFrameInMillis > durationOfTask) {				
				Thread.sleep(minFrameInMillis - durationOfTask);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
