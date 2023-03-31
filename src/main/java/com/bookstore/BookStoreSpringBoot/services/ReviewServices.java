package com.bookstore.BookStoreSpringBoot.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.convert.ReviewAndReviewEntity;
import com.bookstore.BookStoreSpringBoot.entity.ReviewEntity;
import com.bookstore.BookStoreSpringBoot.object.Review;
import com.bookstore.BookStoreSpringBoot.repositories.ReviewRepository;


@Service
public class ReviewServices {
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	ReviewAndReviewEntity convertReviewAndReviewEntity;
	@Transactional
	public List<ReviewEntity> getAllReviewByBookID(long id){
		return reviewRepository.findByBookEntityId(id);
	}

	@Transactional
	public ReviewEntity addNewReview(Review review){
		ReviewEntity reviewEntity =convertReviewAndReviewEntity.convertToReviewEntity(review);
		reviewEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		return  reviewRepository.save(reviewEntity);
	}
}
