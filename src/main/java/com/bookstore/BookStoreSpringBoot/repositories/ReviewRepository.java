package com.bookstore.BookStoreSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.ReviewEntity;


@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{
	
	List<ReviewEntity> findByBookEntityId(long bookId);

}
