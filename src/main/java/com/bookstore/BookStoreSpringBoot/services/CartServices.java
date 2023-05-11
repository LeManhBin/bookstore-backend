package com.bookstore.BookStoreSpringBoot.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.dto.request.CartRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.CartDetailDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.CartResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.StoreBasicInforDTO;
import com.bookstore.BookStoreSpringBoot.entity.CartEntity;
import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;
import com.bookstore.BookStoreSpringBoot.mapper.CartMapper;
import com.bookstore.BookStoreSpringBoot.mapper.StoreMapper;
import com.bookstore.BookStoreSpringBoot.repositories.CartRepository;
@Service
public class CartServices {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CartMapper cartMapper;
	@Autowired
	StoreMapper storeMapper;
	public List<CartResponseDTO> getAllCartDetailByUserId(long userid) throws IOException{
		//Tìm kiếm tất cả các cửa hàng có trong giỏ hàng
		List<StoreEntity> storeIncart = cartRepository.getAllStoreInCart(userid);
		if(storeIncart.size() > 0) {
			//Sắp xếp các sản phẩm theo cửa hàng
			List<CartResponseDTO> cartResponseDTOs  = new ArrayList<CartResponseDTO>();
			StoreBasicInforDTO storeBasicInfor;
			List<CartEntity> cartEntities;
			for(StoreEntity storeEntity:storeIncart) {
				storeBasicInfor = storeMapper.toStoreBasicInforDTO(storeEntity);
				cartEntities = cartRepository.getAllCartDetailByStoreIdAndUserId(storeEntity.getId(), userid);
				List<CartDetailDTO>  cartDetailDTOs = cartMapper.toCartDetailDTOs(cartEntities);
				cartResponseDTOs.add(new CartResponseDTO(storeBasicInfor, cartDetailDTOs));
			}
			return cartResponseDTOs;
		}else
			return null;
	}
	
	public CartEntity getCartDetailByID(long id){
		return cartRepository.findById(id).orElse(null);
	}
	
	public CartEntity getCartDetailByUserIdAndBookId(long userid, long bookid){
		return cartRepository.findByUserEntityIdAndBookEntityId(userid, bookid);
	}

	public CartEntity addNewCart(CartRequestDTO cart){
		CartEntity cartFind = getCartDetailByUserIdAndBookId(cart.getUserId(), cart.getBookId());
		if (cartFind != null) {
			int amount = cartFind.getAmount() + cart.getAmount();
			return updateAmount(cartFind.getId(), amount > 10 ? 10 : amount);
	
		} else {
			CartEntity cartEntity = cartMapper.toCartEntity(cart);
			return  cartRepository.save(cartEntity);
		}
	
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

	public List<StoreEntity> getAllStoreInCart(long userid) {
		return cartRepository.getAllStoreInCart(userid);
	}

	public CartEntity selectToPay(long id) {
		CartEntity cartEntity = getCartDetailByID(id);
		if(cartEntity.getStatus() == 1)
			cartEntity.setStatus(0);
		else
			cartEntity.setStatus(1);
		return cartRepository.save(cartEntity);
	}
	public List<CartEntity> getCartDetailSelected(long userId) {
		List<CartEntity> cartEntities = cartRepository.findByUserEntityIdAndStatus(userId, 1);
		return cartEntities;
	}

	public List<CartResponseDTO> getCartSelectedToPay(long userId) throws IOException {
		List<StoreEntity> storeInCartSelected = cartRepository.getAllStoreInCartSelected(userId, 1);
		if(storeInCartSelected.size() > 0) {
			List<CartResponseDTO> cartResponseDTOs  = new ArrayList<CartResponseDTO>();
			StoreBasicInforDTO storeBasicInfor;
			List<CartEntity> cartEntities;
			for(StoreEntity storeEntity:storeInCartSelected) {
				cartEntities = cartRepository.findByStoreIdAndUserIdAndStatus(storeEntity.getId(), userId, 1);
				if(cartEntities.size() > 0) {
					storeBasicInfor = storeMapper.toStoreBasicInforDTO(storeEntity);
					List<CartDetailDTO>  cartDetailDTOs = cartMapper.toCartDetailDTOs(cartEntities);
					cartResponseDTOs.add(new CartResponseDTO(storeBasicInfor, cartDetailDTOs));
				}
			}
			return cartResponseDTOs;
		}else
			return null;
	
	}

	public void setItemStatus(Long[] cartIds) {
		cartRepository.updateStatusByIdIn(cartIds);
	}

}
