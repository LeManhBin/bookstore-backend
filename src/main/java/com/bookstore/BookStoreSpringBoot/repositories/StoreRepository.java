package com.bookstore.BookStoreSpringBoot.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;


@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>{
	@Query("SELECT COUNT(s) FROM StoreEntity s WHERE s.createDate BETWEEN :startDate AND :endDate")
	long countByCreateDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
