package com.bookstore.BookStoreSpringBoot.dto.request;

import java.sql.Date;
import java.util.List;


import lombok.Data;
@Data
public class BookRequestDTO {
	private String name;
	private String image;
	private String author;
	private long categoryId;
	private String publishing;
	private int publishingYear;
	private long promotionId;
	private long storeId;
	private int pageNumber;
	private int length;
	private int width;
	private int height;
	private float weight;
	private int quantity;
	private float price;
	private int quantitySold;
	private String description;
	private Date createDate;
	private Date updateDate;
	List<Long> tagId;
	private int status;
}
