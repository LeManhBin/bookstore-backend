package com.bookstore.BookStoreSpringBoot.dto.request;

import lombok.Data;

@Data
public class VnpayRequestDTO {
	private String orderType;
	private String amount;
	private String bankCode;
	private String orderInfor;

}
