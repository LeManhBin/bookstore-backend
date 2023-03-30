package com.bookstore.BookStoreSpringBoot.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderDetail")
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="orderid")
	private OrderEntity orderEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="bookid")
	private BookEntity bookEntity;
	@Column
	private int amount;
	@Column
	private int price;
	@Column
	private int percent;
	
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
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public OrderDetailEntity() {
		super();
	}
	
}
