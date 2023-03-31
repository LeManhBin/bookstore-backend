package com.bookstore.BookStoreSpringBoot.entity;

import java.sql.Date;

import jakarta.persistence.*;
@Entity
@Table(name="store")
public class StoreEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JoinColumn(name="idkh", referencedColumnName = "id")
	private UserEntity user;
	@Column
	private String name;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String avatar;
	@Column(name="cover_image")
	private String coverImage;
	@Column
	private String address;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="update_date")
	private Date updateDate;
	@Column
	private int status;
	@OneToMany(mappedBy="storeEntity")
	
	public long getId() {
		return id;
	}
	public StoreEntity() {
		super();
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
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


	@Override
	public String toString() {
		return "Store [id=" + id + ", idkh=" + user.toString() + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", avatar=" + avatar + ", coverImage=" + coverImage + ", address=" + address + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", status=" + status + "]";
	}
}
