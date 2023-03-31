package com.bookstore.BookStoreSpringBoot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.entity.OrderEntity;
import com.bookstore.BookStoreSpringBoot.repositories.OrderRepository;

@Service
public class OrderServices {
	@Autowired
	OrderRepository orderRepository;
	public List<OrderEntity> getOrdersByStoreId(long storeId){
		return orderRepository.findByStoreEntityId(storeId);
	}
	public OrderEntity getOrdersById(long id){
		return orderRepository.findById(id).orElse(null);
	}
	public List<OrderEntity> getOrdersByStoreIdAndStatus(long storeId, int status){
		return orderRepository.findByStoreEntityIdAndStatus(storeId, status);
	}
	
	
}
