package com.bookstore.BookStoreSpringBoot.dto.request;

import lombok.Data;

@Data
public class ReviewRequestDTO {
	private long orderId;
	private long userId;
	private int star;
	private String comment;
}
