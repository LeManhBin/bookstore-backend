package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.ServicePackRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.ServicePackResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.ServicePackEntity;

@Mapper(componentModel = "spring")
public interface ServicePackMapper {
	ServicePackMapper INSTANCE = Mappers.getMapper(ServicePackMapper.class);
	@Mapping(target="id", ignore = true)
	ServicePackEntity toServicePackEntity(ServicePackRequestDTO servicePack);
	
	ServicePackResponseDTO toServicePackResponseDTO(ServicePackEntity servicePackEntity);

	List<ServicePackResponseDTO> toServicePackResponseDTOs(List<ServicePackEntity> servicePackEntities);
}
