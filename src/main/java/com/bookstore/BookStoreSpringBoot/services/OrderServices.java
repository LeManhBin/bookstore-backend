package com.bookstore.BookStoreSpringBoot.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.dto.request.OrderRecipient;
import com.bookstore.BookStoreSpringBoot.dto.request.OrderRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.OrderStoreDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderDetailResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderResponseForStore;
import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.entity.CartEntity;
import com.bookstore.BookStoreSpringBoot.entity.OrderDetailEntity;
import com.bookstore.BookStoreSpringBoot.entity.OrderEntity;
import com.bookstore.BookStoreSpringBoot.mapper.OrderDetailMapper;
import com.bookstore.BookStoreSpringBoot.mapper.OrderMapper;
import com.bookstore.BookStoreSpringBoot.mapper.StoreMapper;
import com.bookstore.BookStoreSpringBoot.repositories.BookRepository;
import com.bookstore.BookStoreSpringBoot.repositories.CartRepository;
import com.bookstore.BookStoreSpringBoot.repositories.OrderDetailRepository;
import com.bookstore.BookStoreSpringBoot.repositories.OrderRepository;
import com.bookstore.BookStoreSpringBoot.repositories.StoreRepository;
import com.bookstore.BookStoreSpringBoot.repositories.UserRepository;

@Service
public class OrderServices {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	StoreMapper storeMapper;
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderDetailMapper orderDetailMapper;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	OrderDetailRepository orderDetailRepositoy;
	public List<OrderResponseForStore> getOrdersByStoreId(long storeId) throws IOException {
		List<OrderEntity> orderEntities =  orderRepository.findByStoreEntityId(storeId);
		if(orderEntities.size() > 0) {
			return orderMapper.toOrderResponseForStore(orderEntities);
		}else
			return null;
	}

	public OrderResponseForStore getOrdersById(long id) throws IOException {
		OrderEntity orderEntity =  orderRepository.findById(id).orElse(null);
		if(orderEntity != null) {
			//Tìm kiếm tất cả các chi tiết sản đơn hàng của đơn hàng
			List<OrderDetailEntity> orderDetailEntities = orderDetailRepositoy.findByOrderEntityId(orderEntity.getId());
			//Mapping các dữ liệu chi tiết đơn hàng cần thiết
			List<OrderDetailResponseDTO> orderDetailResponses = orderDetailMapper.toOrderDetailResponseDTO(orderDetailEntities);
			//Tạo một đối tượng gồm đơn hàng và các chi tiết đơn hàng nằm trong đơn hàng
			OrderResponseForStore orderResponseForStore = orderMapper.toOrderResponseForStore(orderEntity, orderDetailResponses);
			return orderResponseForStore;
		}else
			return null;
	}
	
	public void addNewOrder(OrderRequestDTO orderRequest) {
		//Tìm các cửa hàng trong các sản phẩm được chọn
		List<OrderStoreDTO> orderStores = orderRequest.getStores();
		OrderRecipient orderRecipient = orderRequest.getRecipient();
		//Với mỗi cửa hàng thì tạo một đơn hàng
		for (OrderStoreDTO orderStore : orderStores) {
			OrderEntity orderEntity = createOrderEntity(orderRequest, orderStore, orderRecipient);
			long totalMoney = 0L;
			List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
			for (Long id : orderStore.getCartIds()){
				OrderDetailEntity orderDetailEntity = createOrderDetail(orderEntity, id);
				orderDetailEntity.setOrderEntity(orderEntity);
				orderDetailEntities.add(orderDetailEntity);
				cartRepository.deleteById(id);
			}
			orderEntity.setTotalMoney(orderStore.getTotalMoney());
			orderRepository.save(orderEntity);
			orderDetailRepositoy.saveAll(orderDetailEntities);
		}
	}

	public List<OrderResponseDTO> getOrderByUserId(long userId) throws IOException {
		List<OrderEntity> orderEntities = orderRepository.findByUserEntityId(userId);
		if(orderEntities.size() > 0) {
			return convertOrderEntityToOrderResponseDTO(orderEntities);
		}else
			return null;
		
	}
	public OrderEntity updateOrderStatus(long id, int status) {
		OrderEntity orderEntity = orderRepository.findById(id).get();
		orderEntity.setStatus(status);
		return orderRepository.save(orderEntity);
	}

	private OrderDetailEntity createOrderDetail(OrderEntity orderEntity, long id) {
		CartEntity cartEntity = cartRepository.findById(id).get();
		OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
		BookEntity bookEntity = bookRepository.findById(cartEntity.getBookEntity().getId()).get();
		orderDetailEntity.setOrderEntity(orderEntity);
		orderDetailEntity.setBookEntity(bookEntity);
		orderDetailEntity.setAmount(cartEntity.getAmount());
		orderDetailEntity.setPrice(bookEntity.getPrice());
		orderDetailEntity.setDiscount(
				bookEntity.getPromotionEntity() == null ? 0 : bookEntity.getPromotionEntity().getDiscount());
		return orderDetailEntity;

	}

	public OrderEntity createOrderEntity(OrderRequestDTO orderRequest, OrderStoreDTO orderStore,
			OrderRecipient orderRecipient) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setUserEntity(userRepository.findById(orderRecipient.getUserId()).get());
		orderEntity.setStoreEntity(storeRepository.findById(orderStore.getId()).get());
		orderEntity.setName(orderRecipient.getName());
		orderEntity.setPhone(orderRecipient.getPhone());
		orderEntity.setAddress(orderRecipient.getAddress());
		orderEntity.setPayment(orderRequest.getPayment());
		orderEntity.setNote(orderStore.getNote());
		orderEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		orderEntity.setStatus(0);
		return orderEntity;
	}
	
	public List<OrderResponseDTO> convertOrderEntityToOrderResponseDTO(List<OrderEntity> orderEntities) throws IOException {
		List<OrderResponseDTO> orderResponses = new ArrayList<>();
		for(OrderEntity orderEntity:orderEntities) {
			OrderResponseDTO orderResponse = orderMapper.toOrderResponseDTO(orderEntity);
			List<OrderDetailEntity> orderDetailEntities = orderDetailRepositoy.findByOrderEntityId(orderEntity.getId());
			if(orderDetailEntities.size() > 0) {
				List<OrderDetailResponseDTO> orderDetailResponses = new ArrayList<OrderDetailResponseDTO>();
				for(OrderDetailEntity orderDetail:orderDetailEntities) {
					OrderDetailResponseDTO orderDetailResponse = orderDetailMapper.toOrderDetailResponseDTO(orderDetail);
					orderDetailResponses.add(orderDetailResponse);
				}
				orderResponse.setOrderDetails(orderDetailResponses);
			}
			orderResponses.add(orderResponse);
		}
		return orderResponses;
	}
}
