package com.bookstore.BookStoreSpringBoot.dto.response;

import java.sql.Date;

import lombok.Data;
@Data
public class StoreResponseDTO {
	private long id;
	private String name;
	private String phone;
	private String email;
	private String avatar;
	private String coverImage;
	private Date endDate;
	private AddressResponseDTO address;
	private Date createDate;
	private Date updateDate;
	private int status;
	
}
