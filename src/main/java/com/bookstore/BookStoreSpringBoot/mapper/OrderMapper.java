package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.response.OrderDetailResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderResponseForStore;
import com.bookstore.BookStoreSpringBoot.entity.OrderEntity;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
	
	@Mapping(source = "orderEntity.storeEntity", target = "store")
    OrderResponseDTO toOrderResponseDTO(OrderEntity orderEntity);
	
	OrderResponseForStore toOrderResponseForStore(OrderEntity orderEntity, List<OrderDetailResponseDTO> orderDetails);
	
	@Mapping(target = "store", ignore =  true)
	List<OrderResponseForStore> toOrderResponseForStore(List<OrderEntity> orderEntity);
}
