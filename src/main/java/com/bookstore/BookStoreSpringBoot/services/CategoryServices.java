package com.bookstore.BookStoreSpringBoot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.BookStoreSpringBoot.dto.request.CategoryRequestDTO;
import com.bookstore.BookStoreSpringBoot.entity.CategoryEntity;
import com.bookstore.BookStoreSpringBoot.mapper.CategoryMapper;
import com.bookstore.BookStoreSpringBoot.repositories.CategoryRepository;

@Service
public class CategoryServices {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	CategoryMapper  categoryMapper;
	@Autowired
	ImageStorageService imageStorageService;
	public List<CategoryEntity> getAllCategories(){
		return categoryRepository.findByStatus(0);
	}
	
	public CategoryEntity getCategoriesByID(long id){
		return categoryRepository.findById(id).orElse(null);
	}
	
	public CategoryEntity addNewCategory(CategoryRequestDTO category, MultipartFile file){
		CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(category);
		if(file != null) {
			String fileName=imageStorageService.storeFile(file);
			categoryEntity.setThumbnail(fileName);
		}
		categoryEntity.setStatus(0);
		return categoryRepository.save(categoryEntity);
	}

	public CategoryEntity updateCategory(long id, CategoryRequestDTO category, MultipartFile file){
		CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(category);
		categoryEntity.setId(id);
		if(file != null) {
			String fileName=imageStorageService.storeFile(file);
			categoryEntity.setThumbnail(fileName);
		}
		return categoryRepository.save(categoryEntity);
	}

	public CategoryEntity deleteCategory(long id){
		CategoryEntity categoryEntity = getCategoriesByID(id);
		categoryEntity.setStatus(1);
		return categoryRepository.save(categoryEntity);
	}
}
