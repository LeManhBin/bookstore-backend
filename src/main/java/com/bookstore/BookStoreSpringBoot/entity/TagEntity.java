package com.bookstore.BookStoreSpringBoot.entity;



import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="tag")
public class TagEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private Integer status;
}

