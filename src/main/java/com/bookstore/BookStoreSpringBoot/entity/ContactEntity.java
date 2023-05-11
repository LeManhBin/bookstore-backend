package com.bookstore.BookStoreSpringBoot.entity;

import java.sql.Date;


import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name="contact")
@Data
public class ContactEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String gmail;
	@Column
	private String subject;
	@Column
	private String content;
	@Column(name="create_date")
	private Date createDate;
	@Column
	private Integer status;
	
}
