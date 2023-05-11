package com.bookstore.BookStoreSpringBoot.dto.response;

import java.util.List;

import lombok.Data;
@Data
public class CartResponseDTO {
	public CartResponseDTO(StoreBasicInforDTO storeBasicInfor, List<CartDetailDTO> cartDetailDTOs) {
		this.store = storeBasicInfor;
		this.cartDetails = cartDetailDTOs;
	}
	private StoreBasicInforDTO store;
	private List<CartDetailDTO> cartDetails;
}
