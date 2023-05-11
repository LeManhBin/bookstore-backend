package com.bookstore.BookStoreSpringBoot.dto.response;

import java.sql.Date;
import java.util.List;


import lombok.Data;
@Data
public class OrderResponseForStore {
	private long id;
	private String name;
	private String phone;
	private String address;
	private Date createDate;
	private int payment;
	private int status;
	private List<OrderDetailResponseDTO> orderDetails;
	private long totalMoney;
}
