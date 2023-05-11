package com.bookstore.BookStoreSpringBoot.dto.request;

import java.sql.Date;

import lombok.Data;
@Data
public class UserRequestDTO {
	private long id;
	private String fullName;
	private String email;
	private String phone;
	private String password;
	private String gender;
	private Date   createDate;
	private Date   updateDate;
	private String avatar;
	private AddressRequestDTO address;
	private int    role;
	private int    status;
	private long storeId;

}
