package com.bookstore.BookStoreSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long>{

}
