package com.bookstore.BookStoreSpringBoot.dto.response;

import lombok.Data;

@Data
public class AdminReport {
	private long sumAccount;
	private long newAccount;
	private long sumStore;
	private long newStore;
	private long sumProduct;
	private long sumQuantitySold;
	private long sumOrder;
	private long sumNewOrder;
	private long sumRevunue;
	private long sumNewRevunue;
}
