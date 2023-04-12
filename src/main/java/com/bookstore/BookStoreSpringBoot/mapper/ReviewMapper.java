package com.bookstore.BookStoreSpringBoot.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.response.ReviewResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.ReviewEntity;
@Mapper(componentModel = "spring")
public interface ReviewMapper {
	StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
	 @Mapping(source = "userEntity.fullName", target = "fullName")
    @Mapping(source = "userEntity.avatar", target = "avatar")
		ReviewResponseDTO toReviewResponseDTO(ReviewEntity reviewEntity);
}
