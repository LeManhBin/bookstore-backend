package com.bookstore.BookStoreSpringBoot.dto.response;

import lombok.Data;

@Data
public class CartDetailDTO {
	private long id;
	private long bookId;
	private String image;
	private String name;
	private String author;
	private int price;
	private int discount;
	private int amount;
}
