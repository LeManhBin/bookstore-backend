package com.bookstore.BookStoreSpringBoot.dto.response;

import lombok.Data;

@Data
public class UserBasicInforDTO {
	private int id;
	private String fullName;
	private String email;
	private String phone;
	private String gender;
	private int role;
	private int status;
	private long storeId;
	private String avatar;
	private AddressResponseDTO address;
}
