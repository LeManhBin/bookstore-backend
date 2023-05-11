package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.request.StoreRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.AddressResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.StoreBasicInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.StoreResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.AddressEntity;
import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "address.id", target = "addressEntity.id")
    StoreEntity toStoreEntity(StoreRequestDTO storeRequestDTO);

    @Mapping(source = "addressEntity", target = "address")
    StoreResponseDTO toStoreResponseDTO(StoreEntity storeEntity);

    @Mapping(source = "addressEntity", target = "address")
    List<StoreResponseDTO> toStoreResponseDTO(List<StoreEntity> storeEntity);

    @Mapping(source = "addressEntity", target = "address")
    StoreBasicInforDTO toStoreBasicInforDTO(StoreEntity storeEntity);

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

    default AddressEntity mapAddressDTOToEntity(AddressResponseDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressDTO.getId());
        addressEntity.setProvinceId(addressDTO.getProvinceId());
        addressEntity.setDistrictId(addressDTO.getDistrictId());
        addressEntity.setWardId(addressDTO.getWardId());
        addressEntity.setFullAddress(addressDTO.getFullAddress());
        return addressEntity;
    }
}