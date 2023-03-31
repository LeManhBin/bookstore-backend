package com.bookstore.BookStoreSpringBoot.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.convert.StoreAndStoreEntity;
import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;
import com.bookstore.BookStoreSpringBoot.object.Store;
import com.bookstore.BookStoreSpringBoot.repositories.StoreRepository;

@Service
public class StoreServices {
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	StoreAndStoreEntity	storeAndStoreEntity;
	
	public List<StoreEntity> getAllStore(){
		return storeRepository.findAll();
	}
	
	public StoreEntity getStoreByID(long id){
		return storeRepository.findById(id).orElse(null);
	}
	
	public StoreEntity addNewStore(Store store){
		StoreEntity storeEntity = 	storeAndStoreEntity.convertToStoreEntity(store);
		storeEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		storeEntity.setStatus(0);
		return storeRepository.save(storeEntity);
	}
	
	@Transactional
	public StoreEntity udpateStore(long id, Store store){
		StoreEntity storeEntity = storeAndStoreEntity.convertToStoreEntity(store);
		storeEntity.setId(id);
		storeEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		return storeRepository.save(storeEntity);
	}
	
	@Transactional
	public StoreEntity deleteStore(long id){
		StoreEntity storeEntity = getStoreByID(id);
		storeEntity.setStatus(1);
		return storeRepository.save(storeEntity);
	}
}
