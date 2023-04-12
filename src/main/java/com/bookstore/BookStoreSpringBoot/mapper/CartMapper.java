package com.bookstore.BookStoreSpringBoot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.CartRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.CartDetailDTO;
import com.bookstore.BookStoreSpringBoot.entity.CartEntity;

@Mapper(componentModel = "spring")
public interface CartMapper {
	CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
	@Mapping(target="id", ignore = true)
	@Mapping(source="userId", target="userEntity.id")
	@Mapping(source="bookId", target="bookEntity.id")
	CartEntity toCartEntity(CartRequestDTO cart);
	
	
	@Mapping(source="bookEntity.id", target="bookId")
	@Mapping(source="bookEntity.name", target="name")
	@Mapping(source="bookEntity.author", target="author")
	@Mapping(source="bookEntity.image", target="image")
	@Mapping(source="bookEntity.promotionEntity.discount", target="discount")
	@Mapping(source="bookEntity.price", target="price")
	CartDetailDTO toCartDetailDTO(CartEntity cartEntity);
}
