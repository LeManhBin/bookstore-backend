package com.bookstore.BookStoreSpringBoot.dto.request;

import lombok.Data;

@Data
public class CategoryRequestDTO {
	private long id;
	private String name;
	private String thumbnail;
	private int status;
}
