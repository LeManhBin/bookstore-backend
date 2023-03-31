package com.bookstore.BookStoreSpringBoot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.object.Cart;
import com.bookstore.BookStoreSpringBoot.entity.CartEntity;
import com.bookstore.BookStoreSpringBoot.convert.CartAndCartEntity;
import com.bookstore.BookStoreSpringBoot.repositories.CartRepository;
@Service
public class CartServices {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CartAndCartEntity cartAndCartEntity;
	
	public List<CartEntity> getAllCartDetailByUserId(long userid){
		return cartRepository.findByUserEntityId(userid);
	}
	
	public CartEntity getCartDetailByID(long id){
		return cartRepository.findById(id).orElse(null);
	}
	
	public CartEntity getCartDetailByUserIdAndBookId(long userid, long bookid){
		return cartRepository.findByUserEntityIdAndBookEntityId(userid, bookid);
	}

	public CartEntity addNewCart(Cart cart){
		CartEntity cartEntity = cartAndCartEntity.convertCartToCartEntity(cart);
		return  cartRepository.save(cartEntity);
	}
	public CartEntity updateAmount(long id, int quantity){
		CartEntity cartEntity = getCartDetailByID(id);
		cartEntity.setAmount(quantity);
		return cartRepository.save(cartEntity);
	}
	
	public CartEntity deleteCartDetail(long id){
		cartRepository.deleteById(id);
		return cartRepository.findById(id).orElse(null);
	}
}
