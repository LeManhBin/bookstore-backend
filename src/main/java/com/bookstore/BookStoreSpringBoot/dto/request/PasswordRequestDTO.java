package com.bookstore.BookStoreSpringBoot.dto.request;

import lombok.Data;

@Data
public class PasswordRequestDTO {
	private String email;
	private String oldPassword;
	private String newPassword;
}
