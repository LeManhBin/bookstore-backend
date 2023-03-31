package com.bookstore.BookStoreSpringBoot.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.convert.PromotionAndPromotionEntity;
import com.bookstore.BookStoreSpringBoot.entity.PromotionEntity;
import com.bookstore.BookStoreSpringBoot.object.Promotion;
import com.bookstore.BookStoreSpringBoot.repositories.PromotionRepository;


@Service
public class PromotionServices {
	@Autowired
	PromotionRepository promotionRepository;
	@Autowired
	StoreServices storeServices;
	@Autowired
	PromotionAndPromotionEntity promotionAndPromotionEntity;

	
	public List<PromotionEntity> getAllPromotionByStoreId(long storeId){
		return promotionRepository.findByStoreEntityId(storeId);
	}

	public List<PromotionEntity> getAllPromotionByStoreIdAndStatus(long storeId, int status){
		return promotionRepository.findByStoreEntityIdAndStatus(storeId, status);
	}
	
	public PromotionEntity getPromotionById(long id){
		return promotionRepository.findById(id).orElse(null);
	}
	
	public PromotionEntity addNewPromotion(Promotion promotion){
		PromotionEntity promotionEntity = promotionAndPromotionEntity.convertToPromotionEntity(promotion);
		promotionEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		promotionEntity.setStatus(0);
		return promotionRepository.save(promotionEntity);
	}
	
	public PromotionEntity updatePromotion(long id, Promotion promotion){
		PromotionEntity promotionEntity =  promotionAndPromotionEntity.convertToPromotionEntity(promotion);
		promotionEntity.setId(id);
		promotionEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		return promotionRepository.save(promotionEntity);
	}
	@Transactional
	public PromotionEntity deletePromotion(long id){
		promotionRepository.deleteById(id);
		return getPromotionById(id);
	}
	@Transactional
	public PromotionEntity updatePromotionStatus(long id, int status){
		PromotionEntity promotionEntity = getPromotionById(id);
		promotionEntity.setStatus(status);
		return promotionRepository.save(promotionEntity);
	}
}
