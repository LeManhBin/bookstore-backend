package com.bookstore.BookStoreSpringBoot.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
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
	@OneToOne
	@JoinColumn(name="address_id", referencedColumnName = "id")
	private AddressEntity addressEntity;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="update_date")
	private Date updateDate;
	@Column
	private String avatar;
	@Column
	private Integer role;
	@Column
	private Integer status;
	@OneToOne
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;
	
}
