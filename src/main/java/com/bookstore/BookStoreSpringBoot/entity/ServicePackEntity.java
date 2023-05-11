package com.bookstore.BookStoreSpringBoot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="servicepack")
public class ServicePackEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private Double price; 
	@Column(name="expiration_date")
	private Integer expirationDate; 
	@Column
	private String thumbnail;	
	@Column
	private String description;	
	@Column
	private Integer status;
}
