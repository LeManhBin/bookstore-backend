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
import com.bookstore.BookStoreSpringBoot.dto.request.ReviewRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.CartDetailDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.CartResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderDetailResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderResponseForStore;
import com.bookstore.BookStoreSpringBoot.dto.response.StoreBasicInforDTO;
import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.entity.CartEntity;
import com.bookstore.BookStoreSpringBoot.entity.OrderDetailEntity;
import com.bookstore.BookStoreSpringBoot.entity.OrderEntity;
import com.bookstore.BookStoreSpringBoot.entity.ReviewEntity;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.mapper.OrderDetailMapper;
import com.bookstore.BookStoreSpringBoot.mapper.OrderMapper;
import com.bookstore.BookStoreSpringBoot.mapper.StoreMapper;
import com.bookstore.BookStoreSpringBoot.repositories.BookRepository;
import com.bookstore.BookStoreSpringBoot.repositories.CartRepository;
import com.bookstore.BookStoreSpringBoot.repositories.OrderDetailRepository;
import com.bookstore.BookStoreSpringBoot.repositories.OrderRepository;
import com.bookstore.BookStoreSpringBoot.repositories.StoreRepository;
import com.bookstore.BookStoreSpringBoot.repositories.UserRepository;

/**
 * @author Acer
 *
 */
/**
 * @param orderEntities
 * @return
 * @throws IOException
 */
/**
 * @param orderEntities
 * @return
 * @throws IOException
 */
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
	StorageServices storageServices;
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
			List<OrderDetailEntity> orderDetailEntities = orderDetailRepositoy.findByOrderEntityId(orderEntity.getId());
			List<OrderDetailResponseDTO> orderDetailResponses = orderDetailMapper.toOrderDetailResponseDTO(orderDetailEntities);
			for(int i = 0; i <  orderDetailResponses.size(); i++) {
				if(orderDetailResponses.get(i).getImage() != null) {
					byte[] imageByte = storageServices.convertMultiFileToBytes(orderDetailResponses.get(i).getImage()).get(0);
					orderDetailResponses.get(i).setImagebyte(imageByte);
				}
			}
			OrderResponseForStore orderResponseForStore = orderMapper.toOrderResponseForStore(orderEntity, orderDetailResponses);
			orderResponseForStore.setOrderDetails(orderDetailResponses);
			return orderResponseForStore;
		}else
			return null;
	}
	
	/*Find all store in books
	 * with one store finded, create one order
	 * width one order created, create orderdetail by book selected in cart
	*/
	public void addNewOrder(OrderRequestDTO orderRequest) {
		List<OrderStoreDTO> orderStores = orderRequest.getStores();
		OrderRecipient orderRecipient = orderRequest.getRecipient();
		for (OrderStoreDTO orderStore : orderStores) {
			OrderEntity orderEntity = createOrderEntity(orderRequest, orderStore, orderRecipient);
			orderRepository.save(orderEntity);
			for (Long id : orderStore.getCartIds()) {
				OrderDetailEntity orderDetailEntity = createOrderDetail(orderEntity, id);
				orderDetailRepositoy.save(orderDetailEntity);
				cartRepository.deleteById(id);
			}
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
					if(orderDetail.getBookEntity().getImage() != null) {
						byte[] imageByte = storageServices.convertMultiFileToBytes(orderDetail.getBookEntity().getImage()).get(0);
						orderDetailResponse.setImagebyte(imageByte);
					}
					orderDetailResponses.add(orderDetailResponse);
				}
				orderResponse.setOrderDetails(orderDetailResponses);
			}
			orderResponses.add(orderResponse);
		}
		return orderResponses;
	}

}
