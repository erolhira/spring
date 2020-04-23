package com.javalopment.springcomponents.bookmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javalopment.springcomponents.bookmanagement.entity.Book;
import com.javalopment.springcomponents.bookmanagement.persistence.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Transactional
	public void save(Book book) {
		bookRepository.save(book);
	}
}
