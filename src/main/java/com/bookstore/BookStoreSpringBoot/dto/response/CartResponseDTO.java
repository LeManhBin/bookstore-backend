package com.bookstore.BookStoreSpringBoot.dto.response;

import java.util.List;

public class CartResponseDTO {
	private StoreBasicInforDTO store;
	private List<CartDetailDTO> cartDetails;
	
	public CartResponseDTO() {
		super();
	}
	
	public CartResponseDTO(StoreBasicInforDTO store, List<CartDetailDTO> cartDetails) {
		super();
		this.store = store;
		this.cartDetails = cartDetails;
	}

	public StoreBasicInforDTO getStore() {
		return store;
	}
	public void setStore(StoreBasicInforDTO store) {
		this.store = store;
	}
	public List<CartDetailDTO> getCartDetails() {
		return cartDetails;
	}
	public void setCartDetails(List<CartDetailDTO> cartDetails) {
		this.cartDetails = cartDetails;
	}
	
}
