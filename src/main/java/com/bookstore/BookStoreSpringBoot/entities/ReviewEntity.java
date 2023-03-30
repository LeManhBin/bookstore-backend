package com.bookstore.BookStoreSpringBoot.entities;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Review")
public class ReviewEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="userid")
	private UserEntity userEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="bookid")
	private BookEntity bookEntity;
	@Column
	private int star;
	@Column
	private String comment;
	@Column
	private Date createDate;
	
	public ReviewEntity() {
		super();
	}

	
	public ReviewEntity(UserEntity userEntity, BookEntity bookEntity, int star, String comment,Date createDate) {
		super();
		this.userEntity = userEntity;
		this.bookEntity = bookEntity;
		this.star = star;
		this.comment = comment;
		this.createDate = createDate;
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

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
