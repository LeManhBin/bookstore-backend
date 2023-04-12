package com.bookstore.BookStoreSpringBoot.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.dto.request.PromotionRequestDTO;
import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.entity.PromotionEntity;
import com.bookstore.BookStoreSpringBoot.mapper.PromotionMapper;
import com.bookstore.BookStoreSpringBoot.repositories.BookRepository;
import com.bookstore.BookStoreSpringBoot.repositories.PromotionRepository;
import com.bookstore.BookStoreSpringBoot.repositories.StoreRepository;


@Service
public class PromotionServices {
	@Autowired
	PromotionRepository promotionRepository;
	@Autowired
	StoreServices storeServices;
	@Autowired
	PromotionMapper promotionMapper;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	StoreRepository storeRepository;
	public List<PromotionEntity> getAllPromotionByStoreId(long storeId){
		return promotionRepository.findByStoreEntityId(storeId);
	}

	public List<PromotionEntity> getAllPromotionByStoreIdAndStatus(long storeId, int status){
		return promotionRepository.findByStoreEntityIdAndStatus(storeId, status);
	}
	
	public PromotionEntity getPromotionById(long id){
		return promotionRepository.findById(id).orElse(null);
	}
	
	public PromotionEntity addNewPromotion(PromotionRequestDTO promotion){
		PromotionEntity promotionEntity = promotionMapper.toPromotionEntity(promotion);
		promotionEntity.setStoreEntity(storeRepository.findById(promotion.getStoreId()).orElse(null));
		promotionEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		promotionEntity.setStatus(0);
		promotionEntity =  promotionRepository.save(promotionEntity);
		//cập nhật trạng thái khuyến mãi cho sách
		for(Long id:promotion.getBookIds()) {
			BookEntity bookEntity = bookRepository.findById(id).orElse(null);
			if(bookEntity != null) {
				bookEntity.setPromotionEntity(promotionEntity);
				bookRepository.save(bookEntity);
			}
		}
		return promotionEntity;
	}
	
	public PromotionEntity updatePromotion(long id, PromotionRequestDTO promotion){
		PromotionEntity promotionEntity =  promotionMapper.toPromotionEntity(promotion);
		promotionEntity.setId(id);
		promotionEntity.setStoreEntity(storeRepository.findById(promotion.getStoreId()).orElse(null));
		promotionEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		promotionEntity = promotionRepository.save(promotionEntity);
		bookRepository.setPromotioneNullByPromotioneId(id);
		bookRepository.updatePromotionByBookIdInAndPromotionId(promotion.getBookIds(), promotionEntity);
		return promotionEntity;
	}

	public PromotionEntity deletePromotion(long id){
		promotionRepository.deleteById(id);
		return getPromotionById(id);
	}

	public PromotionEntity updatePromotionStatus(long id, int status){
		PromotionEntity promotionEntity = getPromotionById(id);
		promotionEntity.setStatus(status);
		return promotionRepository.save(promotionEntity);
	}
}
