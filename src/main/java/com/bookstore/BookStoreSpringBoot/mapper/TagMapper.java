package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bookstore.BookStoreSpringBoot.dto.response.TagResponse;
import com.bookstore.BookStoreSpringBoot.entity.TagEntity;

@Mapper(componentModel = "spring")
public interface TagMapper {
	TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
	TagResponse toTagResponse(TagEntity tagEntity);
	List<TagResponse> toTagResponses(List<TagEntity> tagEntity);
}
