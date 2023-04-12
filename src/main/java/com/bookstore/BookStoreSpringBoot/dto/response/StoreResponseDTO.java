package com.bookstore.BookStoreSpringBoot.dto.response;

import java.sql.Date;

public class StoreResponseDTO {
	private long id;
	private String name;
	private String phone;
	private String email;
	private String avatar;
	private String coverImage;
	private byte[] avartByte;
	private byte[] coverImageByte;
	private String address;
	private Date createDate;
	private Date updateDate;
	private int status;
	
	public StoreResponseDTO() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public byte[] getAvartByte() {
		return avartByte;
	}


	public void setAvartByte(byte[] avartByte) {
		this.avartByte = avartByte;
	}


	public byte[] getCoverImageByte() {
		return coverImageByte;
	}


	public void setCoverImageByte(byte[] coverImageByte) {
		this.coverImageByte = coverImageByte;
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
