package com.bookstore.BookStoreSpringBoot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="address")
public class AddressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="province_id")
	private Integer provinceId;
	@Column(name="district_id")
	private Integer districtId;
	@Column(name="ward_id")
	private Integer wardId;
	@Column(name="full_address")
	private String fullAddress;
}
