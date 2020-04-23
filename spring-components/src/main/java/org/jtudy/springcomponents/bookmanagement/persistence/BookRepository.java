package org.jtudy.springcomponents.bookmanagement.persistence;

import org.jtudy.springcomponents.bookmanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
