package com.bookstore.BookStoreSpringBoot.dto.request;

import java.sql.Date;

import lombok.Data;
@Data
public class StoreRequestDTO {
	private long userId;
	private String name;
	private String phone;
	private String email;
	private String avatar;
	private String coverImage;
	private AddressRequestDTO address;
	private Date createDate;
	private Date updateDate;
	private Date endDate;
	private int status;
}
