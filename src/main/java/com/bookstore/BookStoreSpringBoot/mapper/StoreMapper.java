package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.StoreRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.StoreBasicInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.StoreResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;


@Mapper(componentModel = "spring")
public interface StoreMapper {
	StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
	@Mapping(source = "userId", target = "user.id")
	@Mapping(target = "id", ignore = true)
	StoreEntity toStoreEntity(StoreRequestDTO storeRequestDTO);
	StoreResponseDTO toStoreResponseDTO(StoreEntity storeEntity);
	List<StoreResponseDTO> toStoreResponseDTO(List<StoreEntity> storeEntity);
	
	StoreBasicInforDTO toStoreBasicInforDTO(StoreEntity storeEntity);
}
