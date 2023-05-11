package com.bookstore.BookStoreSpringBoot.dto.request;

import lombok.Data;

@Data
public class CartRequestDTO {
	private long userId;
	private long bookId;
	private int amount;
	private int status;
}
