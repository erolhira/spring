package com.javalopment.workshop.springasync;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@EnableScheduling
public class ConsumerService extends ConsumerServiceBase {

	private BlockingQueue<Integer> numberQueue = new LinkedBlockingQueue<>();
	
	@Override
	public void consume() {		
		System.out.println(new Date() + " -- " + Thread.currentThread().getName());
	}

}
