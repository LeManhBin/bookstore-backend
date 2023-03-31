package com.bookstore.BookStoreSpringBoot.object;

import java.sql.Date;
public class Promotion {
	private int id;
	private int storeId;
	private String name;
	private String body;
	private Date startDate;
	private Date endDate;
	private Date createDate;
	private Date updateDate;
	private int status;
	
	public Promotion() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PromotionEntity [id=" + id + ", name=" + name + ", body=" + body + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", storeEntity=" + status + "]";
	}
	
}
