package com.bookstore.BookStoreSpringBoot.entity;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="Review")
@Data
@NoArgsConstructor
public class ReviewEntity {
	@Autowired
	public ReviewEntity(UserEntity userEntity, BookEntity bookEntity, int star, String comment, Date createDate) {
		this.userEntity = userEntity;
		this.bookEntity = bookEntity;
		this.star = star;
		this.comment = comment;
		this.createDate = createDate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="user_id")
	private UserEntity userEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="book_id")
	private BookEntity bookEntity;
	@Column
	private Integer star;
	@Column
	private String comment;
	@Column(name="create_date")
	private Date createDate;
}
