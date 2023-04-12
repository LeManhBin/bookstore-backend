package com.bookstore.BookStoreSpringBoot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.UserRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserBasicInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserFullInforDTO;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	@Mapping(source = "storeEntity.id", target = "storeId")
	UserBasicInforDTO toUserBasicInforDTO(UserEntity userEntity);
	
	@Mapping(source = "storeEntity.id", target = "storeId")
	UserFullInforDTO toUserFullInforDTO(UserEntity userEntity);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "storeId", target = "storeEntity.id")
    UserEntity toUserEntity(UserRequestDTO dto);
}
