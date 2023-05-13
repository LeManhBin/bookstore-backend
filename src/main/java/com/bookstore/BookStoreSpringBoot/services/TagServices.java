package com.bookstore.BookStoreSpringBoot.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.entity.TagEntity;
import com.bookstore.BookStoreSpringBoot.repositories.TagRepository;

@Service
public class TagServices {
	@Autowired
	TagRepository tagRepository;
	
	public List<TagEntity> getALlTag(){
		return tagRepository.findAll();
	}
	public TagEntity getTagByID(long id){
		return tagRepository.findById(id).orElse(null);
	}
	
	public TagEntity addNewTag(TagEntity tagEntity){
		tagEntity.setStatus(0);
		return tagRepository.save(tagEntity);
	}
	
	public TagEntity updateTag(long id, TagEntity tagEntity){
		tagEntity.setId(id);
		tagEntity.setId(id);
		return tagRepository.save(tagEntity);
	}
	public TagEntity deleteTag(long id){
		tagRepository.deleteById(id);
		return getTagByID(id);
	}
}
