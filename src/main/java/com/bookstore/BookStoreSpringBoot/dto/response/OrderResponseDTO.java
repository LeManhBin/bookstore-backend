package com.bookstore.BookStoreSpringBoot.dto.response;

import java.util.List;


import lombok.Data;
@Data
public class OrderResponseDTO {
	private int id;
	private StoreBasicInforDTO store;
	private List<OrderDetailResponseDTO> orderDetails;
	private int payment;
	private int status;
}
