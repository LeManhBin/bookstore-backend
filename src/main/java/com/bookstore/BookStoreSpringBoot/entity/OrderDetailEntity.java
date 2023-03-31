package com.bookstore.BookStoreSpringBoot.entity;
import jakarta.persistence.*;

@Entity
@Table(name="OrderDetail")
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="order_id")
	private OrderEntity orderEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="book_id")
	private BookEntity bookEntity;
	@Column
	private int amount;
	@Column
	private int price;
	@Column
	private int discount;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public OrderEntity getOrderEntity() {
		return orderEntity;
	}
	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public OrderDetailEntity() {
		super();
	}
	
}
