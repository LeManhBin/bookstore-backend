package com.bookstore.BookStoreSpringBoot.dto.response;

import lombok.Data;

@Data
public class ServicePackResponseDTO {
	private long id;
	private String name;
	private double price; 
	private int expirationDate; 
	private String thumbnail;
	private String description;	
	private int status;

}
