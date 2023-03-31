package com.bookstore.BookStoreSpringBoot.entity;

import jakarta.persistence.*;
@Entity
@Table(name="cart")
public class CartEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private UserEntity userEntity;
	@ManyToOne
	@JoinColumn(name="book_id", referencedColumnName = "id")
	private BookEntity bookEntity;
	private int amount;
	public CartEntity() {
		super();
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

	public BookEntity getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
