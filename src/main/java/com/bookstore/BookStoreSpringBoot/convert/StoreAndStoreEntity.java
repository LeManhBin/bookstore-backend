package com.bookstore.BookStoreSpringBoot.convert;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;
import com.bookstore.BookStoreSpringBoot.object.Store;
import com.bookstore.BookStoreSpringBoot.services.UserServices;

@Component
public class StoreAndStoreEntity {
	@Autowired
	UserServices userServices;
	public StoreEntity convertToStoreEntity(Store store) {
		StoreEntity storeEntity = new StoreEntity();
		storeEntity.setUser(userServices.getUserByID(store.getIdkh()));
		storeEntity.setName(store.getName());
		storeEntity.setPhone(store.getPhone());
		storeEntity.setEmail(store.getEmail());
		storeEntity.setAvatar(store.getAvatar());
		storeEntity.setCoverImage(store.getCoverImage());
		storeEntity.setAddress(store.getAddress());
		storeEntity.setCreateDate(store.getCreateDate());
		storeEntity.setUpdateDate(store.getCreateDate());
		storeEntity.setStatus(store.getStatus());
		return storeEntity;
	}
}
