package com.bookstore.BookStoreSpringBoot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.PromotionRequestDTO;
import com.bookstore.BookStoreSpringBoot.entity.PromotionEntity;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
	PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);
	@Mapping(target="id", ignore = true)
	PromotionEntity toPromotionEntity(PromotionRequestDTO promotion);
	
}
