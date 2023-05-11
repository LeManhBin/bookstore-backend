package com.bookstore.BookStoreSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.BookStoreSpringBoot.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long>{
	
}
