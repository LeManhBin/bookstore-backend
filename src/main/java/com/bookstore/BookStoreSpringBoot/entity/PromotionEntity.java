package com.bookstore.BookStoreSpringBoot.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="promotion")
public class PromotionEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private String body;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="update_date")
	private Date updateDate;
	@ManyToOne
	@JoinColumn(name="storeId", referencedColumnName = "id")
	private StoreEntity storeEntity;
	@Column
	private int status;
	public PromotionEntity() {
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
	public StoreEntity getStoreEntity() {
		return storeEntity;
	}
	public void setStoreEntity(StoreEntity storeEntity) {
		this.storeEntity = storeEntity;
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
				+ ", storeEntity=" + storeEntity + ", status=" + status + "]";
	}

	
	
}
