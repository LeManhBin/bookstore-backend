package com.bookstore.BookStoreSpringBoot.dto.request;

import lombok.Data;

@Data
public class AddressRequestDTO {
	private long id;
	private long provinceId;
	private long districtId;
	private long wardId;
	private String fullAddress;
}
