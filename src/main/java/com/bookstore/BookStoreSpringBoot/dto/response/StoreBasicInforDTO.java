package com.bookstore.BookStoreSpringBoot.dto.response;

import lombok.Data;

@Data
public class StoreBasicInforDTO {
	private Long id;
	private String name;
	private String avatar;
	private AddressResponseDTO address;
}
