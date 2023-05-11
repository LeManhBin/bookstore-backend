package com.bookstore.BookStoreSpringBoot.dto.request;

import java.util.List;

import lombok.Data;
@Data
public class OrderStoreDTO {
	private Long id;
	private String note;
	private List<Long> cartIds;
	private Long totalMoney;
}
