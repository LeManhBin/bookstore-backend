package com.bookstore.BookStoreSpringBoot.entity;
import jakarta.persistence.*;


@Entity
@Table(name="servicepack")
public class ServicePackEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private double price; 
	@Column(name="quantity_product")
	private int quantityProduct;
	@Column(name="expiration_date")
	private int expirationDate; 
	@Column
	private String thumbnail;	
	@Column
	private String description;	
	@Column
	private int status;
	public ServicePackEntity() {
		super();
	}
	
	public ServicePackEntity(long id, String name, double price, int quantityProduct, int expirationDate, String thumbnail, String description, int status) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantityProduct = quantityProduct;
		this.expirationDate = expirationDate;
		this.thumbnail = thumbnail;
		this.description = description;
		this.status = status;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantityProduct() {
		return quantityProduct;
	}
	public void setQuantityProduct(int quantityProduct) {
		this.quantityProduct = quantityProduct;
	}
	public int getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(int expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", price=" + price + ", quantityProduct=" + quantityProduct
				+ ", expirationDate=" + expirationDate + ", description=" + description + ", status=" + status + "]";
	}
}
