package com.bookstore.BookStoreSpringBoot.dto.request;

import lombok.Data;

@Data
public class OrderRecipient {
	private long userId;
	private String name;
	private String phone;
	private String address;
	
}
