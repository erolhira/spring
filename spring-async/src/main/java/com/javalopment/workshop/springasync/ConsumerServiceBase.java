package com.javalopment.workshop.springasync;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;

/*
 * @EnableAsync annotation enables Spring to run @Async methods in a background thread pool.
 */
public abstract class ConsumerServiceBase {	
	
	@Value("#{1000 / ${consumer.tps}}")
	private Long minFrameInMillis;
	
	public abstract void consume();
	
	/*
	 * if tps 4 and threadPoolSize is 2;
	 * @Scheduled ignores maxThreadPoolSize and 4 calls in one second are made asynchronously with two threads. 
	 */
	@Async("customTaskExecutor")
    //@Scheduled(fixedRateString = "#{1000 / ${consumer.tps}}")
	@Scheduled(fixedRate = 1)
    public void consumeScheduled() {
        
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {			
			consume();
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
