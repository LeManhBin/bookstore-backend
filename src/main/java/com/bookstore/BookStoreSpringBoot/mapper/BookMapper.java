package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.dto.request.BookRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookBasicInfoDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookExtendInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookFullInforDTO;
@Mapper(componentModel = "spring")
public interface BookMapper {
	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    @Mapping(source = "promotionEntity.discount", target = "discount", defaultValue = "0")
    BookBasicInfoDTO toBookBasicInforDTO(BookEntity bookEntity);
    @Mapping(source = "promotionEntity.discount", target = "discount", defaultValue = "0")
    List<BookBasicInfoDTO> toBookBasicInforDTO(List<BookEntity> bookEntity);
    BookExtendInforDTO toBookExtendInforDTO(BookEntity bookEntity);
    
    @Mapping(source = "categoryEntity.id", target = "categoryId")
    @Mapping(source = "categoryEntity.name", target = "category")
    @Mapping(source = "promotionEntity.discount", target = "discount", defaultValue = "0")
    BookFullInforDTO toBookFullInforDTO(BookEntity bookEntity);
   
    @Mapping( target = "id", ignore = true)
    @Mapping(source = "categoryId", target = "categoryEntity.id")
    @Mapping(source = "storeId", target = "storeEntity.id")
    BookEntity toBookEntity(BookRequestDTO book);
    
    
    
}