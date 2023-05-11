package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.CartRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.CartDetailDTO;
import com.bookstore.BookStoreSpringBoot.entity.CartEntity;

@Mapper(componentModel = "spring")
public interface CartMapper {
	CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

	@Mappings({ @Mapping(target = "id", ignore = true), @Mapping(source = "userId", target = "userEntity.id"),
			@Mapping(source = "bookId", target = "bookEntity.id"),
			@Mapping(source = "status", target = "status", defaultValue = "0") })

	CartEntity toCartEntity(CartRequestDTO cart);

	@Mappings({ @Mapping(source = "bookEntity.id", target = "bookId"),
			@Mapping(source = "bookEntity.name", target = "name"),
			@Mapping(source = "bookEntity.author", target = "author"),
			@Mapping(target = "image", expression = "java(com.bookstore.BookStoreSpringBoot.mapper.MapperUtils.getOneBookImage(cartEntity.getBookEntity().getImage()))"),
			@Mapping(source = "bookEntity.promotionEntity.discount", target = "discount"),
			@Mapping(source = "bookEntity.price", target = "price") })

	CartDetailDTO toCartDetailDTO(CartEntity cartEntity);

	@Mappings({ @Mapping(source = "bookEntity.id", target = "bookId"),
			@Mapping(source = "bookEntity.name", target = "name"),
			@Mapping(source = "bookEntity.author", target = "author"),
			@Mapping(target = "image", expression = "java(com.bookstore.BookStoreSpringBoot.mapper.MapperUtils.getOneBookImage(cartEntity.BookEntity().getImage()))"),
			@Mapping(source = "bookEntity.promotionEntity.discount", target = "discount"),
			@Mapping(source = "bookEntity.price", target = "price") })
	List<CartDetailDTO> toCartDetailDTOs(List<CartEntity> cartEntity);
}
