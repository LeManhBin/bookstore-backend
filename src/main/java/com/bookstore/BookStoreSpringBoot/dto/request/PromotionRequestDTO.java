package com.bookstore.BookStoreSpringBoot.dto.request;

import java.sql.Date;
import java.util.List;

import lombok.Data;
@Data
public class PromotionRequestDTO {
	private long id;
	private long storeId;
	private String name;
	private String body;
	private int discount;
	private Date startDate;
	private Date endDate;
	private Date createDate;
	private Date updateDate;
	private List<Long> bookIds;
	private int status;
}
