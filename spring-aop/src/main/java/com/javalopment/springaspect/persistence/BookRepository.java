package com.javalopment.springaspect.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javalopment.springaspect.annotations.Duration;
import com.javalopment.springaspect.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Duration
	Optional<Book> findById(Long id);
}
