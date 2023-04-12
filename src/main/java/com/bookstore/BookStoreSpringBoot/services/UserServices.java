package com.bookstore.BookStoreSpringBoot.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.BookStoreSpringBoot.dto.request.PasswordRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.UserRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserBasicInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserFullInforDTO;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.mapper.UserMapper;
import com.bookstore.BookStoreSpringBoot.repositories.UserRepository;

import javassist.NotFoundException;

@Service
public class UserServices {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserMapper userMapper;
	@Autowired
	StorageServices storageServices;

	public List<UserBasicInforDTO> getAllUser() throws IOException{
		List<UserEntity> userEntities = userRepository.findAll();
		List<UserBasicInforDTO> userBasicInforDTOs = new ArrayList<UserBasicInforDTO>();
		UserBasicInforDTO userBasicInforDTO;
		for(UserEntity userEntity:userEntities) {
			userBasicInforDTO = userMapper.toUserBasicInforDTO(userEntity);
			if (userEntity.getAvatar() != null) {
				byte[] imageBytes = storageServices.convertFileToByte(userEntity.getAvatar());
				userBasicInforDTO.setImageBytes(imageBytes);
			}
			userBasicInforDTOs.add(userBasicInforDTO);
		}
		return userBasicInforDTOs;
	}
	public UserFullInforDTO getUserByID(long id) throws IOException{
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		if(userEntity != null) {
			UserFullInforDTO userFullInforDTO = userMapper.toUserFullInforDTO(userEntity);
			if(userEntity.getAvatar() != null) {
				byte[] imageBytes = storageServices.convertFileToByte(userEntity.getAvatar());
				userFullInforDTO.setImageBytes(imageBytes);
			}
			return userFullInforDTO;
		}else
			return null;
	}
	
	public UserEntity addNewUser(UserRequestDTO user, MultipartFile file) throws IOException{
		UserEntity userEntity = userMapper.toUserEntity(user);
		if(file != null) {
			String avatarBytes = storageServices.saveFile(file);
			userEntity.setAvatar(avatarBytes);
		}
		userEntity.setCreateDate(Date.valueOf(LocalDate.now()));	
		userEntity.setStatus(0);
		return userRepository.save(userEntity);
		
	}
	
	public UserEntity updateUser(long id, UserRequestDTO user, MultipartFile file) throws IOException{
		UserEntity userEntity = userMapper.toUserEntity(user);
		userEntity.setId(id);
		String password = userRepository.findById(id).get().getPassword();
		userEntity.setPassword(password);
		userEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		if(file != null) {
			String avatarPath = storageServices.saveFile(file);
			userEntity.setAvatar(avatarPath);
		}
		return userRepository.save(userEntity);
	}	
	
	public UserBasicInforDTO findUserByEmail(String key) throws IOException {
		UserEntity userEntity = userRepository.findByEmail(key);
		if(userEntity != null) {
			UserBasicInforDTO userBasicInforDTO = userMapper.toUserBasicInforDTO(userEntity);
			if(userEntity.getAvatar() != null) {
				byte[] imageBytes = storageServices.convertFileToByte(userEntity.getAvatar());
				userBasicInforDTO.setImageBytes(imageBytes);
			}
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
		UserEntity userEntity = userRepository.findByEmailAndPassword(email, password);
		if(userEntity != null) {
			UserBasicInforDTO userBasicInforDTO = userMapper.toUserBasicInforDTO(userEntity);
			if(userEntity.getAvatar() != null) {
				byte[] imageBytes = storageServices.convertFileToByte(userEntity.getAvatar());
				userBasicInforDTO.setImageBytes(imageBytes);
			}
			return userBasicInforDTO;
		}else
			return null;
	}
	public boolean updatePasword(long id, PasswordRequestDTO passwordRequestDTO) throws NotFoundException {
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
			throw new NotFoundException("Tài khoản không tồn tai");
		}
	}
	public boolean resetPassword(PasswordRequestDTO passwordRequestDTO) throws NotFoundException {
		UserEntity userEntity = userRepository.findByEmail(passwordRequestDTO.getEmail());
		if(userEntity != null) {
			userEntity.setPassword(passwordRequestDTO.getNewPassword());
			userRepository.save(userEntity);
			return true;
		}else {
			throw new NotFoundException("Tài khoản không tồn tai");
		}
	}
}
