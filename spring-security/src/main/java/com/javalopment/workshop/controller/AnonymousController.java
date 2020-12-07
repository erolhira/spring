package com.javalopment.workshop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anonym")
public class AnonymousController {

	@RequestMapping("/test")
	public String test() {		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return "anonym endpoint works";
	}
}
