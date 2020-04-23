package com.javalopment.springaspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalopment.springaspect.annotations.Duration;
import com.javalopment.springaspect.entity.Book;
import com.javalopment.springaspect.persistence.BookRepository;

@Service
public class SimpleService {

	@Autowired BookRepository bookRepository;
	
	@Duration
	public void print() {
		System.out.println("SimpleService.print is called...");
	}
	
	public String call1() {
		Book book = bookRepository.findById(1L).get();
		return "book: " + book.getName();
	}
}
