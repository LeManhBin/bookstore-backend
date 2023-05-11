package com.bookstore.BookStoreSpringBoot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.CategoryRequestDTO;
import com.bookstore.BookStoreSpringBoot.entity.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	CategoryEntity toCategoryEntity(CategoryRequestDTO category);
}
