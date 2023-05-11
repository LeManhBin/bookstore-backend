package com.bookstore.BookStoreSpringBoot.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;
@Data 
@Entity
@Table(name="store")
public class StoreEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
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
	@OneToOne
	@JoinColumn(name="address_id", referencedColumnName = "id")
	private AddressEntity addressEntity;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="update_date")
	private Date updateDate;
	@Column(name="end_date")
	private Date endDate;
	@Column
	private Integer status;
}
