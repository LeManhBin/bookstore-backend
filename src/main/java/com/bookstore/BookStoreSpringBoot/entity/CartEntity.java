package com.bookstore.BookStoreSpringBoot.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="cart")
public class CartEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private UserEntity userEntity;
	@ManyToOne
	@JoinColumn(name="book_id", referencedColumnName = "id")
	private BookEntity bookEntity;
	@Column
	private Integer amount;
	@Column
	private Integer status;
}
