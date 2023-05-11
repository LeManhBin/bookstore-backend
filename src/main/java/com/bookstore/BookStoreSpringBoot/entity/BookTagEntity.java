package com.bookstore.BookStoreSpringBoot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "book_tag")
public class BookTagEntity {
	@Id
	@Column(name = "book_id")
	private Long bookId;

	@Id
	@Column(name = "tag_id")
	private Long tagId;

}
