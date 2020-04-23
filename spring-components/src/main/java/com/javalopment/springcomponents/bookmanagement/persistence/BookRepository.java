package com.javalopment.springcomponents.bookmanagement.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javalopment.springcomponents.bookmanagement.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
