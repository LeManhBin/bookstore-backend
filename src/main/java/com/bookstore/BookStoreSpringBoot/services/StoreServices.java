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
import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.mapper.StoreMapper;
import com.bookstore.BookStoreSpringBoot.repositories.StoreRepository;
import com.bookstore.BookStoreSpringBoot.repositories.UserRepository;

@Service
public class StoreServices {
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	StorageServices	storageServices;
	@Autowired
	StoreMapper	storeMapper;
	@Autowired
	UserRepository	userRepository;
	public List<StoreResponseDTO> getAllStore() throws IOException{
		List<StoreEntity> stores = storeRepository.findAll();
		List<StoreResponseDTO> storeResponseDTOs = new ArrayList<StoreResponseDTO>();
		StoreResponseDTO storeResponseDTO;
		for(StoreEntity store:stores) {
			storeResponseDTO = storeMapper.toStoreResponseDTO(store);
			if(store.getAvatar() != null) {
				byte[] avatar = storageServices.convertFileToByte(store.getAvatar());
				storeResponseDTO.setAvartByte(avatar);
			}
			if(store.getCoverImage() != null) {
				byte[] coverImage = storageServices.convertFileToByte(store.getCoverImage());
				storeResponseDTO.setCoverImageByte(coverImage);
			}
			storeResponseDTOs.add(storeResponseDTO);
		}
		return storeMapper.toStoreResponseDTO(stores);
	}
	
	public StoreResponseDTO getStoreByID(long id) throws IOException{
		StoreEntity store = storeRepository.findById(id).orElse(null);
		if(store != null) {
			StoreResponseDTO storeResponseDTO = storeMapper.toStoreResponseDTO(store);
			if(store.getAvatar() != null) {
				byte[] avatar = storageServices.convertFileToByte(store.getAvatar());
				storeResponseDTO.setAvartByte(avatar);
			}
			if(store.getCoverImage() != null) {
				byte[] coverImage = storageServices.convertFileToByte(store.getCoverImage());
				storeResponseDTO.setCoverImageByte(coverImage);
			}
			return storeResponseDTO;
		}
		else 
			return null;
	}
	
	public StoreEntity addNewStore(StoreRequestDTO store, MultipartFile avatar, MultipartFile coverImage) throws IOException{
		StoreEntity storeEntity = storeMapper.toStoreEntity(store);
		if (avatar != null) {
			String avatarPath = storageServices.saveFile(avatar);
			storeEntity.setAvatar(avatarPath);
		}
		if (coverImage != null) {
			String coverImagePath = storageServices.saveFile(coverImage);
			storeEntity.setCoverImage(coverImagePath);
		}
		storeEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		storeEntity.setStatus(0);
		storeEntity = storeRepository.save(storeEntity);
		UserEntity userEntity = userRepository.findById(store.getUserId()).get();
		userEntity.setStoreEntity(storeEntity);
		userRepository.save(userEntity);
		return storeRepository.save(storeEntity);
	}
	
	public StoreEntity udpateStore(long id, StoreRequestDTO store, MultipartFile avatar, MultipartFile coverImage) throws IOException{
		StoreEntity storeEntity = storeMapper.toStoreEntity(store);
		if (avatar != null) {
			String avatarPath = storageServices.saveFile(avatar);
			storeEntity.setAvatar(avatarPath);
		}
		if (coverImage != null) {
			String coverImagePath = storageServices.saveFile(coverImage);
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
}
