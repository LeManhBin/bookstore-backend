package com.bookstore.BookStoreSpringBoot.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
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
	@Column
	private Date createDay;
	@Column
	private Date updateDay;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getCreateDay() {
		return createDay;
	}
	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}
	public Date getUpdateDay() {
		return updateDay;
	}
	public void setUpdateDay(Date updateDay) {
		this.updateDay = updateDay;
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
	
	public UserEntity(long id, String name, String email, String phone, String password, String gender, String address,
			Date createDay, Date updateDay, String avatar, int role, int status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.createDay = createDay;
		this.updateDay = updateDay;
		this.avatar = avatar;
		this.role = role;
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ ", gender=" + gender + ", address=" + address + ", createDay=" + createDay + ", updateDay="
				+ updateDay + ", avatar=" + avatar + ", role=" + role + ", status=" + status + "]";
	}
	public UserEntity(String name, String email, String phone, String password, String gender, String address,
			int role) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.role = role;
	}
	
}
