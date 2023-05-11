package com.bookstore.BookStoreSpringBoot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.AddressRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.AddressResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
	AddressResponseDTO toAddressResponseDTO(AddressEntity addressEntity);
	
	@Mapping(target="id", ignore = true)
	AddressEntity toAddressEntity(AddressRequestDTO addressRequest);
}
