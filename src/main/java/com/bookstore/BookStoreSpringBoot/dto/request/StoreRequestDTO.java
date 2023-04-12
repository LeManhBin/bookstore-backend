package com.bookstore.BookStoreSpringBoot.dto.request;

import java.sql.Date;

public class StoreRequestDTO {
	private long userId;
	private String name;
	private String phone;
	private String email;
	private String avatar;
	private String coverImage;
	private String address;
	private Date createDate;
	private Date updateDate;
	private int status;
	
	public StoreRequestDTO() {
		super();
	}

	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getCoverImage() {
		return coverImage;
	}


	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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
}
