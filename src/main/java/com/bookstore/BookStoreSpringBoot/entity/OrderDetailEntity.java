package com.bookstore.BookStoreSpringBoot.entity;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="OrderDetail")
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="order_id")
	private OrderEntity orderEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="book_id")
	private BookEntity bookEntity;
	@Column
	private Integer amount;
	@Column
	private Integer price;
	@Column
	private Integer discount;
	
}
