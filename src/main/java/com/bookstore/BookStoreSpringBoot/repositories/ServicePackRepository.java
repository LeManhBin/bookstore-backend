package com.bookstore.BookStoreSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.ServicePackEntity;


@Repository
public interface ServicePackRepository extends JpaRepository<ServicePackEntity, Long>{

}
