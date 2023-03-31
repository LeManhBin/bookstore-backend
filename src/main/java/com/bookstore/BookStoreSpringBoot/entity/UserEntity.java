package com.bookstore.BookStoreSpringBoot.entity;

import java.sql.Date;

import jakarta.persistence.*;
@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@Column(name="full_name")
	private String fullName;
	@Column
	private String email;
	@Column
	private String phone;
	@Column
	private String password;
	@Column
	private String gender;
	@Column
	private String address;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="update_date")
	private Date updateDate;
	@Column
	private String avatar;
	@Column
	private int role;
	@Column
	private int status;
	@OneToOne(mappedBy="userEntity", cascade = CascadeType.ALL)
	@OneToMany(mappedBy="userEntity", cascade = CascadeType.ALL)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public UserEntity() {
		super();
	}
	
	public UserEntity(long id, String fullName, String email, String phone, String password, String gender, String address,
			Date createDate, Date updateDate, String avatar, int role, int status) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.avatar = avatar;
		this.role = role;
		this.status = status;
	}
}
