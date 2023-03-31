package com.bookstore.BookStoreSpringBoot.object;

import java.sql.Date;

public class Review {
	private long id;
	private long bookId;
	private long userId;
	private int star;
	private String comment;
	private Date createDate;
	
	public Review() {
		super();
	}

	public Review(long id, long bookId, long userId, int star, String comment, Date createDate) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.star = star;
		this.comment = comment;
		this.createDate = createDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
