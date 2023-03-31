package com.bookstore.BookStoreSpringBoot.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.PromotionEntity;


@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, Long>{
	List<PromotionEntity> findByStoreEntityId(long storeId);
	List<PromotionEntity> findByStoreEntityIdAndStatus(long storeId, int status);
}
