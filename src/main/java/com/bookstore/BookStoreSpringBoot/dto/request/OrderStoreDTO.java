package com.bookstore.BookStoreSpringBoot.dto.request;

import java.util.List;

public class OrderStoreDTO {
	private long id;
	private String note;
	private List<Long> cartIds;
	
	public OrderStoreDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Long> getCartIds() {
		return cartIds;
	}
	public void setCartIds(List<Long> cartIds) {
		this.cartIds = cartIds;
	}
	
}
