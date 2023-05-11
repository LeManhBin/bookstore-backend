package com.bookstore.BookStoreSpringBoot.dto.response;

import java.util.Date;
import java.util.List;


import lombok.Data;
@Data
public class BookFullInforDTO {
	private int id;
	private String name;
	private String author;
	private long categoryId;
	private String category;
	private String publishing;
	private int publishingYear;
	private StoreBasicInforDTO store;
	private int promotionId;
	private int discount;
	private int pageNumber;
	private int length;
	private int width;
	private int height;
	private int weight;
	private int quantity;
	private int quantitySold;
	private int price;
	private Date createDate;
	private Date updateDate;
	private String description;
	private int status;
	private  List<String> images;
	private List<TagResponse> tags;
	
}
