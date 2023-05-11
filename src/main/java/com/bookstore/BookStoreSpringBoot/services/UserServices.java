package com.bookstore.BookStoreSpringBoot.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.BookStoreSpringBoot.dto.request.PasswordRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.UserRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserBasicInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserFullInforDTO;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.mapper.UserMapper;
import com.bookstore.BookStoreSpringBoot.repositories.AddressRepository;
import com.bookstore.BookStoreSpringBoot.repositories.UserRepository;
@Service
public class UserServices {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	AddressServices addressServices;
	@Autowired
	UserMapper userMapper;
	@Autowired
	ImageStorageService storageServices;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public List<UserBasicInforDTO> getAllUser() throws IOException{
		List<UserEntity> userEntities = userRepository.findAll();
		if(userEntities.size() > 0)
			return userMapper.toUserBasicInforDTOs(userEntities);
		else 
			return null;
	}
	public UserFullInforDTO getUserByID(long id) throws IOException{
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		if(userEntity != null) {
			return userMapper.toUserFullInforDTO(userEntity);
		}else
			return null;
	}
	
	public UserEntity addNewUser(UserRequestDTO user, MultipartFile file) throws Exception{
		if(!userRepository.existsByEmail(user.getEmail())) {	
			UserEntity userEntity = userMapper.toUserEntity(user);
			if(file != null) {
				String fileName = storageServices.storeFile(file);
				userEntity.setAvatar(fileName);
			}
			
			if (user.getStoreId() == 0) 
				userEntity.setStoreEntity(null);
			userEntity.setAddressEntity(addressServices.addNewAddress(user.getAddress()));
			userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			userEntity.setCreateDate(Date.valueOf(LocalDate.now()));	
			userEntity.setStatus(0);
			return userRepository.save(userEntity);
		}else {
			throw new Exception("Tài khoản này đã tồn tại!");
		}
		
	}
	public UserEntity registerAccount(UserRequestDTO account) throws Exception {
		if(!userRepository.existsByEmail(account.getEmail())) {	
			UserEntity userEntity = userMapper.toUserEntity(account);
			userEntity.setStoreEntity(null);
			userEntity.setPassword(passwordEncoder.encode(account.getPassword()));
			userEntity.setCreateDate(Date.valueOf(LocalDate.now()));	
			userEntity.setStatus(0);
			return userRepository.save(userEntity);
		}else {
			throw new Exception("Tài khoản này đã tồn tại!");
		}
		
	}
	public UserEntity updateUser(long id, UserRequestDTO user, MultipartFile file) throws IOException{
		UserEntity userEntity = userMapper.toUserEntity(user);
		if (user.getStoreId() == 0) 
			userEntity.setStoreEntity(null);
		userEntity.setId(id);
		userEntity.setAddressEntity(addressRepository.findById(user.getAddress().getId()).orElse(null));
	
		String password = userRepository.findById(id).get().getPassword();
		userEntity.setPassword(password);
		userEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		if(file != null) {
			String avatarPath = storageServices.storeFile(file);
			userEntity.setAvatar(avatarPath);
		}
		return userRepository.save(userEntity);
	}	
	
	public UserBasicInforDTO findUserByEmail(String key) throws IOException {
		UserEntity userEntity = userRepository.findByEmail(key);
		
		if(userEntity != null) {
			UserBasicInforDTO userBasicInforDTO = userMapper.toUserBasicInforDTO(userEntity);
			return userBasicInforDTO;
		}else
			return null;
	}	
	public UserEntity deleteUser(long id) {
		UserEntity userEntity = userRepository.findById(id).get();
		userEntity.setStatus(1);
		return userRepository.save(userEntity);
	}
	
	 public UserBasicInforDTO checkLogIn(String email, String password) throws IOException {
	        UserEntity userEntity = userRepository.findByEmail(email);
	        if (userEntity != null && passwordEncoder.matches(password, userEntity.getPassword())) {
	            return userMapper.toUserBasicInforDTO(userEntity);
	        } else {
	            return null;
	        }
	    }
	public boolean updatePasword(long id, PasswordRequestDTO passwordRequestDTO){
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		if(userEntity != null) {
			if(passwordRequestDTO.getOldPassword().equals(userEntity.getPassword())) {
				userEntity.setPassword(passwordRequestDTO.getNewPassword());
				userRepository.save(userEntity);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	public boolean resetPassword(PasswordRequestDTO passwordRequestDTO) {
		UserEntity userEntity = userRepository.findByEmail(passwordRequestDTO.getEmail());
		if(userEntity != null) {
			userEntity.setPassword(passwordEncoder.encode(passwordRequestDTO.getNewPassword()));
			userRepository.save(userEntity);
			return true;
		}else {
			return false;
		}
	}
}
