package com.bookstore.BookStoreSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>{
	List<CartEntity> findByUserEntityId(long id);
	
	CartEntity findByUserEntityIdAndBookEntityId(long userId, long bookId);
}
