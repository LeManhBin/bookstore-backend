package com.bookstore.BookStoreSpringBoot.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.entity.OrderDetailEntity;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>{

	List<OrderDetailEntity> findByOrderEntityId(long id);

	@Query("SELECT bd.bookEntity " +
	           "FROM OrderDetailEntity bd " +
	           "JOIN bd.orderEntity o " +
	           "JOIN bd.bookEntity b " +
	           "WHERE o.createDate >= :startOfWeek " +
	           "AND o.createDate < :endOfWeek " +
	           "GROUP BY bd.bookEntity " +
	           "ORDER BY SUM(bd.amount) DESC")
    List<BookEntity> findBestSellingBooksOfWeek(LocalDateTime startOfWeek, LocalDateTime endOfWeek);

	
}
