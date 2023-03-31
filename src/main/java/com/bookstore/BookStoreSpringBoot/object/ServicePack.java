package com.bookstore.BookStoreSpringBoot.object;

public class ServicePack {
	private long id;
	private String name;
	private double price;
	private int quantityProduct;
	private int expirationDate; 
	private String thumbnail;
	private String description;	
	private int status;
	

	public ServicePack() {
		super();
	}
	
	public ServicePack(long id, String name, double price, int quantityProduct, int expirationDate, String thumbnail, String description, int status) {
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
	public void setStatus(int isActive) {
		this.status = isActive;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", price=" + price + ", quantityProduct=" + quantityProduct
				+ ", expiry=" + expirationDate + ", description=" + description + ", status=" + status + "]";
	}
	
}
