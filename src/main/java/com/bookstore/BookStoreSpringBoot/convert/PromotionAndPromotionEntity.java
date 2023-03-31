package com.bookstore.BookStoreSpringBoot.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.BookStoreSpringBoot.object.Promotion;
import com.bookstore.BookStoreSpringBoot.entity.PromotionEntity;
import com.bookstore.BookStoreSpringBoot.services.StoreServices;

@Component
public class PromotionAndPromotionEntity {
	@Autowired
	StoreServices storeServices;
	public PromotionEntity convertToPromotionEntity(Promotion promotion) {
		PromotionEntity promotionEntity = new PromotionEntity();
		promotionEntity.setName(promotion.getName());
		promotionEntity.setBody(promotion.getBody());
		promotionEntity.setStartDate(promotion.getStartDate());
		promotionEntity.setEndDate(promotion.getEndDate());
		promotionEntity.setCreateDate(promotion.getCreateDate());
		promotionEntity.setUpdateDate(promotion.getUpdateDate());
		promotionEntity.setStoreEntity(storeServices.getStoreByID(promotion.getStoreId()));
		promotionEntity.setStatus(promotion.getStatus());
		return promotionEntity;
	}
}
