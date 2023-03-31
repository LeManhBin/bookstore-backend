package com.bookstore.BookStoreSpringBoot.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.BookStoreSpringBoot.entity.ReviewEntity;
import com.bookstore.BookStoreSpringBoot.object.Review;
import com.bookstore.BookStoreSpringBoot.services.BookServices;
import com.bookstore.BookStoreSpringBoot.services.UserServices;
@Component
public class ReviewAndReviewEntity {
	@Autowired 
	UserServices userServices;
	@Autowired 
	BookServices bookServices;
	public ReviewEntity convertToReviewEntity(Review review) {
		ReviewEntity reviewEntity = new ReviewEntity();
		reviewEntity.setId(review.getId());
		reviewEntity.setUserEntity(userServices.getUserByID(review.getUserId()));
		reviewEntity.setBookEntity(bookServices.getBookByID(review.getBookId()));
		reviewEntity.setStar(review.getStar());
		reviewEntity.setCreateDate(review.getCreateDate());
		return reviewEntity;
	}
}
