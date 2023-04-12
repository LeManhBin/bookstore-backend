package com.bookstore.BookStoreSpringBoot.dto.request;

public class CartRequestDTO {
	private long userId;
	private long bookId;
	private int amount;
	public CartRequestDTO() {
		super();
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
