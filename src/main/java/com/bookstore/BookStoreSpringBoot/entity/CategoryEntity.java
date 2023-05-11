package com.bookstore.BookStoreSpringBoot.entity;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="categories")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String thumbnail;
	@Column
	private Integer status;
}
