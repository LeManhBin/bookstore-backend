package com.bookstore.BookStoreSpringBoot.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.entity.TagEntity;
import com.bookstore.BookStoreSpringBoot.object.Tag;
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
	
	public TagEntity addNewTag(Tag tag){
		TagEntity tagEntity = new TagEntity();
		tagEntity.setName(tag.getName());
		return tagRepository.save(tagEntity);
	}
	
	public TagEntity updateTag(long id, Tag tag){
		TagEntity tagEntity = getTagByID(id);
		tagEntity.setName(tag.getName());
		tagEntity.setStatus(tag.getStatus());
		return tagRepository.save(tagEntity);
	}
	@Transactional
	public TagEntity deleteTag(long id){
		TagEntity tagEntity = getTagByID(id);
		tagEntity.setStatus(1);
		return tagRepository.save(tagEntity);
	}
}
