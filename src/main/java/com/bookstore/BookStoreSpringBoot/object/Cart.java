package com.bookstore.BookStoreSpringBoot.object;

public class Cart {
	private long id;
	private long userid;
	private long bookid;
	private int amount;
	public Cart() {
		super();
	}
	public Cart(long id, long userid, long bookid, int amount) {
		super();
		this.id = id;
		this.userid = userid;
		this.bookid = bookid;
		this.amount = amount;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getBookid() {
		return bookid;
	}
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
