package com.bookstore.BookStoreSpringBoot.dto.response;
import lombok.Data;

@Data
public class AddressResponseDTO {
	private long id;
	private int provinceId;
	private int districtId;
	private int wardId;
	private String fullAddress;
}
