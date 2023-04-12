package com.bookstore.BookStoreSpringBoot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_tag")
public class BookTagEntity {
	@Id
	@Column(name = "book_id")
	private Long bookId;

	@Id
	@Column(name = "tag_id")
	private Long tagId;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public BookTagEntity() {
		super();
	}

}
