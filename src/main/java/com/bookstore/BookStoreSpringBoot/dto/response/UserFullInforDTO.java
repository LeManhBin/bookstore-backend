package com.bookstore.BookStoreSpringBoot.dto.response;

import lombok.Data;

@Data
public class UserFullInforDTO {
	private int id;
	private String fullName;
	private String email;
	private String phone;
	private String gender;
	private AddressResponseDTO address;
	private String createDate;
	private String updateDate;
	private String avatar;
	private int role;
	private int status;
	private int storeId;
}
