package com.bookstore.BookStoreSpringBoot.dto.request;

public class Account {
	private String email;
	private String password;
	
	public Account() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
