package com.jtudy.springaspect;

import org.springframework.stereotype.Service;

import com.jtudy.springaspect.annotations.Duration;

@Service
public class SimpleService {

	@Duration
	public void print() {
		System.out.println("SimpleService.print is called...");
	}
	
	public String call1() {
		return "call1 is called...";
	}
}
