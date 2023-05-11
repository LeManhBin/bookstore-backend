package com.bookstore.BookStoreSpringBoot.dto.request;

import java.util.List;

import lombok.Data;
@Data
public class OrderRequestDTO {
	private List<OrderStoreDTO> stores;
	private OrderRecipient recipient;
	private int payment;

}
