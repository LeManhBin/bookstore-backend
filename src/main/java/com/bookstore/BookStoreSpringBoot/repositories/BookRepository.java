package com.bookstore.BookStoreSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{
	List<BookEntity> findByStoreEntityId(long storeId);
	List<BookEntity> findByStoreEntityIdAndStatus(long storeId, int status);
	List<BookEntity> findByCategoryEntityIdAndStatus(long id, int status);
}
