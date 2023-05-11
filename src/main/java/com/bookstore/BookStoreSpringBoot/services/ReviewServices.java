package com.bookstore.BookStoreSpringBoot.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.BookStoreSpringBoot.dto.request.ReviewRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.ReviewResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.entity.OrderDetailEntity;
import com.bookstore.BookStoreSpringBoot.entity.ReviewEntity;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.mapper.ReviewMapper;
import com.bookstore.BookStoreSpringBoot.repositories.OrderDetailRepository;
import com.bookstore.BookStoreSpringBoot.repositories.ReviewRepository;
import com.bookstore.BookStoreSpringBoot.repositories.UserRepository;


@Service
public class ReviewServices {
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	OrderDetailRepository orderDetailRepositoy;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ReviewMapper reviewMapper;

	
	public void addNewReview(ReviewRequestDTO review) {
		List<OrderDetailEntity> orderDetailEntities = orderDetailRepositoy.findByOrderEntityId(review.getOrderId());
		Date createDate = Date.valueOf(LocalDate.now());
		List<ReviewEntity> reviewEntities = new ArrayList<ReviewEntity>();
		for(OrderDetailEntity orderDetailEntity:orderDetailEntities) {
			BookEntity bookEntity = orderDetailEntity.getBookEntity();
			UserEntity userEntity = userRepository.findById(review.getUserId()).get();
			ReviewEntity reviewEntity = new ReviewEntity(userEntity, bookEntity, review.getStar(), review.getComment(),createDate );
			reviewEntities.add(reviewEntity);
		}
		reviewRepository.saveAll(reviewEntities);
	}


	public List<ReviewResponseDTO> getAllReviewByBookId(long id) throws IOException {
		List<ReviewEntity> reviewEntities = reviewRepository.findByBookEntityId(id);
		System.out.println("vao serrice");
		if(reviewEntities.size() > 0) {
			List<ReviewResponseDTO> reviewResponses = reviewMapper.toReviewResponseDTOs(reviewEntities);
			return reviewResponses;
		}else
			return null;
	}
}
