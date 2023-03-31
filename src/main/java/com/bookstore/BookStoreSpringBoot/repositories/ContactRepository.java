package com.bookstore.BookStoreSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.ContactEntity;


@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long>{
	List<ContactEntity> findByStatus(int status);
}
