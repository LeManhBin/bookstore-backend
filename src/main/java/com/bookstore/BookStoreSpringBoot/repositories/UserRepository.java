package com.bookstore.BookStoreSpringBoot.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);

	UserEntity findByEmailAndPassword(String email, String password);

	@Query("SELECT COUNT(u) FROM UserEntity u WHERE u.role = 1")
	long countByRoleId();

	@Query("SELECT COUNT(u) FROM UserEntity u WHERE u.createDate BETWEEN :startDate AND :endDate")
	long countByCreateDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	boolean existsByEmail(String email);

}
