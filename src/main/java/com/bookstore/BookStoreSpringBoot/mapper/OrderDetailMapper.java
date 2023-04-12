package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.response.OrderDetailResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.OrderDetailEntity;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
	OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);
	@Mapping(source="bookEntity.name", target="name")
	@Mapping(source="bookEntity.id", target="bookId")
	@Mapping(source="bookEntity.author", target="author")
	@Mapping(source="bookEntity.image", target="image")
	OrderDetailResponseDTO toOrderDetailResponseDTO(OrderDetailEntity orderDetail);
	
	@Mapping(source="bookEntity.name", target="name")
	@Mapping(source="bookEntity.id", target="bookId")
	@Mapping(source="bookEntity.author", target="author")
	@Mapping(source="bookEntity.image", target="image")
	List<OrderDetailResponseDTO> toOrderDetailResponseDTO(List<OrderDetailEntity> orderDetail);
}
