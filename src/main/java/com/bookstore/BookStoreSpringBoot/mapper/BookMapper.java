package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.dto.request.BookRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookBasicInfoDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookExtendInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookFullInforDTO;

@Mapper(componentModel = "spring")
public interface BookMapper {
	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	@Mappings({ @Mapping(source = "promotionEntity.discount", target = "discount", defaultValue = "0"),
			@Mapping(target = "image", expression = "java(com.bookstore.BookStoreSpringBoot.mapper.MapperUtils.getOneBookImage(bookEntity.getImage()))") })
	BookBasicInfoDTO toBookBasicInforDTO(BookEntity bookEntity);

	@Mapping(source = "promotionEntity.discount", target = "discount", defaultValue = "0")
	@Mapping(target = "image", expression = "java(com.bookstore.BookStoreSpringBoot.mapper.MapperUtils.getOneBookImage(bookEntity.getImage()))")
	List<BookBasicInfoDTO> toBookBasicInforDTO(List<BookEntity> bookEntity);

	@Mapping(target = "images", expression = "java(com.bookstore.BookStoreSpringBoot.mapper.MapperUtils.getListBookImage(bookEntity.getImage()))")
	BookExtendInforDTO toBookExtendInforDTO(BookEntity bookEntity);

	@Mapping(source = "promotionEntity.discount", target = "discount", defaultValue = "0")
	List<BookExtendInforDTO> toBookExtendInforDTO(List<BookEntity> bookEntities);

	@Mappings({ @Mapping(source = "categoryEntity.id", target = "categoryId"),
			@Mapping(source = "categoryEntity.name", target = "category"),
			@Mapping(source = "promotionEntity.id", target = "promotionId"),
			@Mapping(source = "promotionEntity.discount", target = "discount", defaultValue = "0"),
			@Mapping(target = "images", expression = "java(com.bookstore.BookStoreSpringBoot.mapper.MapperUtils.getListBookImage(bookEntity.getImage()))"),
			@Mapping(source = "storeEntity.id", target = "store.id"),
			@Mapping(source = "storeEntity.name", target = "store.name"),
			@Mapping(source = "storeEntity.avatar", target = "store.avatar"),
			@Mapping(target = "tags", expression = "java(com.bookstore.BookStoreSpringBoot.mapper.MapperUtils.getListTagsBook(bookEntity.getTags()))") })
	BookFullInforDTO toBookFullInforDTO(BookEntity bookEntity);

	@Mappings({ @Mapping(target = "id", ignore = true), @Mapping(source = "categoryId", target = "categoryEntity.id"),
			@Mapping(source = "storeId", target = "storeEntity.id"),
			@Mapping(source = "promotionId", target = "promotionEntity.id", defaultValue = "null") })
	BookEntity toBookEntity(BookRequestDTO book);
}