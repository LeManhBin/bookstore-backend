package com.bookstore.BookStoreSpringBoot.dto.request;

import lombok.Data;

@Data
public class ServicePackRequestDTO {
	private String name;
	private double price; 
	private int quantityProduct;
	private int expirationDate; 
	private String thumbnail;
	private String description;	
	private int status;
}
