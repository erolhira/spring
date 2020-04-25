package com.javalopment.workshop.springasync;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;

/*
 * @EnableAsync annotation enables Spring to run @Async methods in a background thread pool.
 */
public abstract class ConsumerServiceBase {	
	
	@Value("#{1000 / (${consumer.tps.max} / ${pool.size.max})}")
	private Long minFrameInMillis;
	
	public abstract void consumeSingle();
	public abstract void consumeMultiple();
	
	@Async("customTaskExecutor")    
	@Scheduled(fixedDelay = 1000, initialDelay = 1000000000L)
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
	@Scheduled(fixedRate = 1)
    public void consumeMultipleAtATime() {
        
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {			
			consumeMultiple();
		} finally {
			stopWatch.stop();
			sleep(stopWatch.getLastTaskTimeMillis());
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
