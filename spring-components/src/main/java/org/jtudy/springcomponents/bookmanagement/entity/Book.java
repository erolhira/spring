package org.jtudy.springcomponents.bookmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Book {

	@Id
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "author")
	private String author;
}
