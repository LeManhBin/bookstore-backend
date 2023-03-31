package com.bookstore.BookStoreSpringBoot.object;

public class Tag {
	private long id;
	private String name;
	private int status;
	public Tag() {
		super();
	}
	public Tag(long id, String name, int status) {
		super();
		this.id = id;
		this.name = name;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
	
}
