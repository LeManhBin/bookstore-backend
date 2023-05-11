package com.bookstore.BookStoreSpringBoot.dto.response;



import lombok.Data;

@Data
public class BookBasicInfoDTO {
	private int id;
	private String name;
	private String author;
	private int price;
	private int discount;
	private String image;
	private int status;
	private int quantity;
}
