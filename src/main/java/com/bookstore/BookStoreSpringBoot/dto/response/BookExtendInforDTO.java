package com.bookstore.BookStoreSpringBoot.dto.response;


import java.util.List;

import lombok.Data;

@Data
public class BookExtendInforDTO {
	private int id;
	private String name;
	private String author;
	private int price;
	private int quantity;
	private int quantitySold;
	private List<String> images;
	
}
