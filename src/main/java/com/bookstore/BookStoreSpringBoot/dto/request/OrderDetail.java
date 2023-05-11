package com.bookstore.BookStoreSpringBoot.dto.request;

import lombok.Data;

@Data
public class OrderDetail {
	private long id;
	private long orderid;
	private long bookid;
	private int amount;
	private int price;
	private int discount;

}
