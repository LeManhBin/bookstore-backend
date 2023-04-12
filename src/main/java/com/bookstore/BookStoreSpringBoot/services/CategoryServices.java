package com.bookstore.BookStoreSpringBoot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.entity.CategoryEntity;
import com.bookstore.BookStoreSpringBoot.repositories.CategoryRepository;

@Service
public class CategoryServices {
	@Autowired
	CategoryRepository categoryRepository;
	public List<CategoryEntity> getAllCategories(){
		return categoryRepository.findByStatus(0);
	}
	
	public CategoryEntity getCategoriesByID(long id){
		return categoryRepository.findById(id).orElse(null);
	}
	
	public CategoryEntity addNewCategory(CategoryEntity category){
		return categoryRepository.save(category);
	}

	public CategoryEntity updateCategory(long id, CategoryEntity category){
		category.setId(id);
		return categoryRepository.save(category);
	}

	public CategoryEntity deleteCategory(long id){
		CategoryEntity categoryEntity = getCategoriesByID(id);
		categoryEntity.setStatus(1);
		return categoryRepository.save(categoryEntity);
	}
}
