package com.bookstore.BookStoreSpringBoot.dto.request;

import java.sql.Date;
import java.util.List;

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
	public PromotionRequestDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Long> getBookIds() {
		return bookIds;
	}
	public void setBookIds(List<Long> bookIds) {
		this.bookIds = bookIds;
	}
	
}
