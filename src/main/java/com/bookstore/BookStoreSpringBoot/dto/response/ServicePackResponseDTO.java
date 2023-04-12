package com.bookstore.BookStoreSpringBoot.dto.response;


public class ServicePackResponseDTO {
	private long id;
	private String name;
	private double price; 
	private int quantityProduct;
	private int expirationDate; 
	private String thumbnail;
	private byte[] thumbnailByte;
	private String description;	
	private int status;
	
	public byte[] getThumbnailByte() {
		return thumbnailByte;
	}
	public void setThumbnailByte(byte[] thumbnailByte) {
		this.thumbnailByte = thumbnailByte;
	}
	public ServicePackResponseDTO() {
		super();
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
	
}
