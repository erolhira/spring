package com.javalopment.workshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class FormController {

	@RequestMapping("/test")
	public String test(HttpServletRequest request) {
		System.out.println("role is USER: " + request.isUserInRole("USER"));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return "form endpoint works";
	}
}
