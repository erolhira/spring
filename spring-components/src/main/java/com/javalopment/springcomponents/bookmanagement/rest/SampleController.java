package com.javalopment.springcomponents.bookmanagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javalopment.springcomponents.bookmanagement.entity.Book;
import com.javalopment.springcomponents.bookmanagement.service.BookService;
import com.javalopment.springcomponents.customermanagement.service.IService;
import com.javalopment.springcomponents.customermanagement.service.StateService;

@RestController
public class SampleController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private StateService stateService;
	
	@RequestMapping("/save")
	public String saveBooks(){
		
		Book book1 = new Book(3L, "Book 3", "Author 3");
		bookService.save(book1);
		
		return "saved.";
	}
	
	@RequestMapping("/dynamic")
	public String performDynamic(@RequestParam String serviceName) {
		stateService.setServiceName(serviceName);
		IService dynamicService = applicationContext.getBean("dynamicService", IService.class);
		return dynamicService.perform();
	}
}
