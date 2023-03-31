package com.bookstore.BookStoreSpringBoot.object;

public class Category {
	private long id;
	private String name;
	private String thumbnail;
	private int status;
	
	public Category() {
		super();
	}
	public Category(long id, String name, String thumbnail, int status) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
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
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", thumbnail=" + thumbnail + ", status=" + status + "]";
	}
}
