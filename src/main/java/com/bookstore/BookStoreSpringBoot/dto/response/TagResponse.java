package com.bookstore.BookStoreSpringBoot.dto.response;

public class TagResponse {
	private int id;
	private String name;
	public TagResponse() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
