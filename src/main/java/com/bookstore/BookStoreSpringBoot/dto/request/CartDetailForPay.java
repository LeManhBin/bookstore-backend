package com.bookstore.BookStoreSpringBoot.dto.request;

import java.util.List;

import lombok.Data;
@Data
public class CartDetailForPay {
	private long userId;
	private List<Long> bookId;

}
