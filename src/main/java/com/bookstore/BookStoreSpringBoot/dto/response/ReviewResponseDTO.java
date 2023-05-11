package com.bookstore.BookStoreSpringBoot.dto.response;

import java.sql.Date;


import lombok.Data;
@Data
public class ReviewResponseDTO {
	private String fullName;
	private String avatar;
	private int star;
	private Date createDate;
	private String comment;


}
