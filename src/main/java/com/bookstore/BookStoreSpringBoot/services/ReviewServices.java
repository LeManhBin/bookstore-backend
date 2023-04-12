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
	StorageServices storageServices;
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
		if(reviewEntities.size() > 0) {
			List<ReviewResponseDTO> reviewResponses = new ArrayList<>();
			ReviewResponseDTO reviewResponse;
			for(ReviewEntity reviewEntity:reviewEntities) {
				reviewResponse = reviewMapper.toReviewResponseDTO(reviewEntity);
				if(reviewResponse.getAvatar() != null) {
					reviewResponse.setImage(storageServices.convertFileToByte(reviewResponse.getAvatar()));
				}
				reviewResponses.add(reviewResponse);
			}
			return reviewResponses;
		}else
			return null;
	}
}
