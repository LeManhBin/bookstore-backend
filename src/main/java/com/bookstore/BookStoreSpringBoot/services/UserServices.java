package com.bookstore.BookStoreSpringBoot.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.convert.UserAndUserEntity;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.object.User;
import com.bookstore.BookStoreSpringBoot.repositories.UserRepository;

@Service
public class UserServices {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserAndUserEntity userAndUserEntity;
	

	public List<UserEntity> getAllUser(){
		return userRepository.findAll();
	}
	public UserEntity getUserByID(long id){
		return userRepository.findById(id).orElse(null);
	}
	
	public UserEntity addNewUser(User user){
		UserEntity userEntity = userAndUserEntity.convertToUserEntity(user);
		userEntity.setCreateDate(Date.valueOf(LocalDate.now()));	
		userEntity.setStatus(0);
		return userRepository.save(userEntity);
	}
	
	public UserEntity updateUser(long id, User user){
		UserEntity userEntity = userAndUserEntity.convertToUserEntity(user);
		userEntity.setId(id);
		userEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		return userRepository.save(userEntity);
	}	
	@Transactional
	public UserEntity findUserByEmail(String key) {
		return userRepository.findByEmail(key);
	}	
	@Transactional
	public UserEntity deleteUser(long id) {
		UserEntity userEntity = getUserByID(id);
		userEntity.setStatus(1);
		return userRepository.save(userEntity);
	}
	
	@Transactional
	public UserEntity checkLogIn(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
}
