package com.bookstore.BookStoreSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStoreSpringBoot.entity.CartEntity;
import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
	List<CartEntity> findByUserEntityId(long id);

	CartEntity findByUserEntityIdAndBookEntityId(long userId, long bookId);

	@Query("SELECT distinct  bookEntity.storeEntity FROM CartEntity WHERE userEntity.id =:userId")
	List<StoreEntity> getAllStoreInCart(@Param("userId") long userId);

	@Query("SELECT distinct  bookEntity.storeEntity FROM CartEntity WHERE userEntity.id =:userId and status=:status")
	List<StoreEntity> getAllStoreInCartSelected(@Param("userId") long userId, @Param("status") int status);

	@Query("SELECT c FROM CartEntity c WHERE bookEntity.storeEntity.id =:storeId and userEntity.id=:userId")
	List<CartEntity> getAllCartDetailByStoreIdAndUserId(@Param("storeId") long storeId, @Param("userId") long userId);

	List<CartEntity> findByUserEntityIdAndStatus(long userId, int status);

	@Query("SELECT c FROM CartEntity c JOIN c.bookEntity b JOIN c.userEntity u WHERE c.id IN :cartIds AND b.storeEntity.id = :storeId AND u.id = :userId")
	List<CartEntity> findByCartIdAndStoreIdAndUserId(Long[] cartIds, long storeId, long userId);

	@Transactional
	@Modifying
	@Query("UPDATE CartEntity c SET c.status = 1 WHERE c.id IN :ids")
	void updateStatusByIdIn(Long[] ids);

	@Query("SELECT c FROM CartEntity c WHERE c.bookEntity.storeEntity.id = :storeId AND c.userEntity.id = :userId AND c.status = :status")
	List<CartEntity> findByStoreIdAndUserIdAndStatus(long storeId, long userId, int status);

}
