package com.bookstore.BookStoreSpringBoot.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.BookStoreSpringBoot.dto.request.StoreRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.StoreResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.AddressEntity;
import com.bookstore.BookStoreSpringBoot.entity.ServicePackEntity;
import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.mapper.StoreMapper;
import com.bookstore.BookStoreSpringBoot.repositories.ServicePackRepository;
import com.bookstore.BookStoreSpringBoot.repositories.StoreRepository;
import com.bookstore.BookStoreSpringBoot.repositories.UserRepository;

@Service
public class StoreServices {
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	ImageStorageService	storageServices;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressServices addressServices;
	@Autowired
	ServicePackRepository servicesPackRepository;
	@Autowired
	StoreMapper	storeMapper;
	
	public List<StoreResponseDTO> getAllStore() throws IOException{
		List<StoreEntity> stores = storeRepository.findAll();
		List<StoreResponseDTO> storeResponseDTOs = new ArrayList<StoreResponseDTO>();
		StoreResponseDTO storeResponseDTO;
		for(StoreEntity store:stores) {
			storeResponseDTO = storeMapper.toStoreResponseDTO(store);
			storeResponseDTOs.add(storeResponseDTO);
		}
		return storeMapper.toStoreResponseDTO(stores);
	}
	
	public StoreResponseDTO getStoreByID(long id) throws IOException{
		StoreEntity store = storeRepository.findById(id).orElse(null);
		if(store != null) {
			StoreResponseDTO storeResponseDTO = storeMapper.toStoreResponseDTO(store);
			return storeResponseDTO;
		}
		else 
			return null;
	}

	public StoreEntity addNewStore(StoreRequestDTO store, MultipartFile avatar, MultipartFile coverImage) throws IOException{
		StoreEntity storeEntity = storeMapper.toStoreEntity(store);
		if (avatar != null) {
			String avatarPath = storageServices.storeFile(avatar);
			storeEntity.setAvatar(avatarPath);
		}
		if (coverImage != null) {
			String coverImagePath = storageServices.storeFile(coverImage);
			storeEntity.setCoverImage(coverImagePath);
		}
		AddressEntity address = addressServices.addNewAddress(store.getAddress());
		storeEntity.setAddressEntity(address);
		storeEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		storeEntity.setStatus(0);
		storeEntity = storeRepository.save(storeEntity);
		storeEntity =  storeRepository.save(storeEntity);
		UserEntity userEntity = userRepository.findById(store.getUserId()).get();
		userEntity.setStoreEntity(storeEntity);
		userRepository.save(userEntity);
		return storeEntity;
	}
	
	public StoreEntity udpateStore(long id, StoreRequestDTO store, MultipartFile avatar, MultipartFile coverImage) throws IOException{
		StoreEntity storeEntity = storeMapper.toStoreEntity(store);
		System.out.println("file"+avatar.getOriginalFilename() + "========"+store.getAvatar());
		if (avatar != null) {
			
			String avatarPath = storageServices.storeFile(avatar);
			storeEntity.setAvatar(avatarPath);
		}
		if (coverImage != null) {
			String coverImagePath = storageServices.storeFile(coverImage);
			storeEntity.setCoverImage(coverImagePath);
		}
		storeEntity.setId(id);
		storeEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		return storeRepository.save(storeEntity);
	}
	
	public StoreEntity deleteStore(long id){
		StoreEntity storeEntity = storeRepository.findById(id).orElse(null);
		if(storeEntity != null) {
			storeEntity.setStatus(1);
			return storeRepository.save(storeEntity);
		}else
			return null;
		
	}

	public StoreEntity updateStoreEndDate(long userId, long serviceId) {
		
		UserEntity userEntity = userRepository.findById(userId).orElse(null);
		ServicePackEntity servicePack = servicesPackRepository.findById(serviceId).orElse(null);
		if(userEntity != null && servicePack != null) {
			StoreEntity store = userEntity.getStoreEntity();
			int numDay = servicePack.getExpirationDate();
			Date sqlDate = Date.valueOf(LocalDate.now());
	        long millis = sqlDate.getTime();
	        long millisToAdd = numDay * 24L * 60L * 60L * 1000L;
	        long millisAfterAddingDays = millis + millisToAdd;
	        Date dateAfterAddingDays = new Date(millisAfterAddingDays);
	        store.setEndDate(dateAfterAddingDays);
	        return storeRepository.save(store);
		}else {
			return null;
		}
		
	}
}
