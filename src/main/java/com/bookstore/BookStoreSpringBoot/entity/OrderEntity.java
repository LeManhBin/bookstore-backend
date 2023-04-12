package com.bookstore.BookStoreSpringBoot.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="Orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private UserEntity userEntity;
	@OneToOne
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;
	@Column
	private String name;
	@Column
	private String phone;
	@Column
	private String address;
	@Column(name="create_date")
	private Date createDate;
	@Column
	private String note;
	@Column
	private int status;
	@Column
	private int payment;

	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public StoreEntity getStoreEntity() {
		return storeEntity;
	}
	public void setStoreEntity(StoreEntity storeEntity) {
		this.storeEntity = storeEntity;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public OrderEntity() {
		super();
	}
	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", userEntity=" + userEntity + ", storeEntity=" + storeEntity + ", name="
				+ name + ", phone=" + phone + ", address=" + address + ", createDate=" + createDate + ", note=" + note
				+ ", status=" + status + ", payment=" + payment + "]";
	}
	
}
