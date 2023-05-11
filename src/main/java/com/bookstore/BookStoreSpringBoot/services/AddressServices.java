package com.bookstore.BookStoreSpringBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.dto.request.AddressRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.AddressResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.AddressEntity;
import com.bookstore.BookStoreSpringBoot.mapper.AddressMapper;
import com.bookstore.BookStoreSpringBoot.repositories.AddressRepository;
@Service
public class AddressServices {

	@Autowired
	AddressRepository addressRepository;
	@Autowired
	AddressMapper addressMapper;
	public AddressResponseDTO getAddressById(long id) {
		AddressEntity addressEntity = addressRepository.findById(id).orElse(null);
		if(addressEntity != null)
			return addressMapper.toAddressResponseDTO(addressEntity);
		else
			return null;
	}
	
	public AddressEntity addNewAddress(AddressRequestDTO addressRequest) {
		AddressEntity addressEntity =addressMapper.toAddressEntity(addressRequest);
		return addressRepository.save(addressEntity);
	}
	
	public AddressEntity udpateAddress(Long id, AddressRequestDTO addressRequest) {
		AddressEntity addressEntity = addressMapper.toAddressEntity(addressRequest);
		addressEntity.setId(id);
		return addressRepository.save(addressEntity);
	}
	
}
