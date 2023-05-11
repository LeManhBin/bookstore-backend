package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.UserRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.AddressResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserBasicInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserFullInforDTO;
import com.bookstore.BookStoreSpringBoot.entity.AddressEntity;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	@Mapping(source = "storeEntity.id", target = "storeId")
	@Mapping(source = "addressEntity", target = "address")
	UserBasicInforDTO toUserBasicInforDTO(UserEntity userEntity);
	
	@Mapping(source = "storeEntity.id", target = "storeId")
	@Mapping(source = "addressEntity", target = "address")
	UserFullInforDTO toUserFullInforDTO(UserEntity userEntity);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "storeId", target = "storeEntity.id")
	@Mapping(source = "address.id", target = "addressEntity.id", defaultValue = "null")
    UserEntity toUserEntity(UserRequestDTO dto);
	
	@Mapping(source = "storeEntity.id", target = "storeId")
	@Mapping(source = "addressEntity", target = "address")
	List<UserBasicInforDTO> toUserBasicInforDTOs(List<UserEntity> userEntities);
	
	 default AddressResponseDTO mapAddressEntityToDTO(AddressEntity addressEntity) {
	        if (addressEntity == null) {
	            return null;
	        }
	        AddressResponseDTO addressDTO = new AddressResponseDTO();
	        addressDTO.setId(addressEntity.getId());
	        addressDTO.setProvinceId(addressEntity.getProvinceId());
	        addressDTO.setDistrictId(addressEntity.getDistrictId());
	        addressDTO.setWardId(addressEntity.getWardId());
	        addressDTO.setFullAddress(addressEntity.getFullAddress());
	        return addressDTO;
	    }
}
