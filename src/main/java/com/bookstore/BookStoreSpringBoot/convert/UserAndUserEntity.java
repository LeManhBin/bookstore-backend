package com.bookstore.BookStoreSpringBoot.convert;
import org.springframework.stereotype.Component;

import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.object.User;

@Component
public class UserAndUserEntity {
	
	public UserEntity convertToUserEntity(User user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFullName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setPhone(user.getPhone());
		userEntity.setPassword(user.getPassword());
		userEntity.setGender(user.getGender());
		userEntity.setAddress(user.getAddress());
		userEntity.setCreateDate(user.getCreateDay());
		userEntity.setUpdateDate(user.getUpdateDay());
		userEntity.setAvatar(user.getAvatar());
		userEntity.setRole(user.getRole()); 
		userEntity.setStatus(user.getStatus());
		return userEntity;
	}
}
