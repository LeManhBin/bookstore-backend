package com.bookstore.BookStoreSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;


@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>{
	
}
