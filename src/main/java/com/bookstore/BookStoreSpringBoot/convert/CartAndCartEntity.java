package com.bookstore.BookStoreSpringBoot.convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.BookStoreSpringBoot.object.Cart;
import com.bookstore.BookStoreSpringBoot.entity.CartEntity;
import com.bookstore.BookStoreSpringBoot.services.BookServices;
import com.bookstore.BookStoreSpringBoot.services.UserServices;
@Component
public class CartAndCartEntity {
	@Autowired
	UserServices userServices;
	@Autowired
	BookServices bookServices;
	public CartEntity convertCartToCartEntity(Cart cart) {
		CartEntity cartEntity = new CartEntity();
		cartEntity.setId(cart.getId());
		cartEntity.setUserEntity(userServices.getUserByID(cart.getUserid()));
		cartEntity.setBookEntity(bookServices.getBookByID(cart.getBookid()));
		cartEntity.setAmount(cart.getAmount());
		return cartEntity;
	}
}
