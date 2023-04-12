package com.bookstore.BookStoreSpringBoot.dto.response;

import java.util.List;

public class OrderResponseDTO {
	private int id;
	private StoreBasicInforDTO store;
	private List<OrderDetailResponseDTO> orderDetails;
	private int payment;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StoreBasicInforDTO getStore() {
		return store;
	}
	public void setStore(StoreBasicInforDTO store) {
		this.store = store;
	}

	public List<OrderDetailResponseDTO> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailResponseDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
