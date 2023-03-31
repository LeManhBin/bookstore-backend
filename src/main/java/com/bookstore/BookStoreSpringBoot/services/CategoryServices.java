package com.bookstore.BookStoreSpringBoot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.object.Category;
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
	
	public CategoryEntity addNewCategory(Category category){
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName(category.getName());
		categoryEntity.setThumbnail(category.getThumbnail());
		categoryEntity.setStatus(0);
		return categoryRepository.save(categoryEntity);
	}

	public CategoryEntity updateCategory(long id, Category category){
		CategoryEntity categoryEntity = getCategoriesByID(id);
		categoryEntity.setName(category.getName());
		categoryEntity.setThumbnail(category.getThumbnail());
		return categoryRepository.save(categoryEntity);
	}

	public CategoryEntity deleteCategory(long id){
		CategoryEntity categoryEntity = getCategoriesByID(id);
		categoryEntity.setStatus(1);
		return categoryRepository.save(categoryEntity);
	}
}
