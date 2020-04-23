package org.jtudy.springcomponents.bookmanagement.service;

import org.jtudy.springcomponents.bookmanagement.entity.Book;
import org.jtudy.springcomponents.bookmanagement.persistence.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Transactional
	public void save(Book book) {
		bookRepository.save(book);
	}
}
