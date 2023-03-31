package com.bookstore.BookStoreSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.OrderEntity;



@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	
	List<OrderEntity> findByStoreEntityId(long storeId);
	List<OrderEntity> findByStoreEntityIdAndStatus(long storeId, int status);

}
