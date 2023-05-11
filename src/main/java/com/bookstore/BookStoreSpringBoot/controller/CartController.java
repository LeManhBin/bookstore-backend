package com.bookstore.BookStoreSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.BookStoreSpringBoot.dto.request.CartRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.CartResponseDTO;
import com.bookstore.BookStoreSpringBoot.object.ResponseObject;
import com.bookstore.BookStoreSpringBoot.services.CartServices;
@CrossOrigin
@RestController
@RequestMapping("/BookStore/api")
public class CartController {
	@Autowired
	CartServices cartServices;
	
	@GetMapping("carts/{userid}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllCartDetailByUserId(@PathVariable long userid) {
		try {
			List<CartResponseDTO> cartRequestDTOs = cartServices.getAllCartDetailByUserId(userid);
			if (cartRequestDTOs!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", cartRequestDTOs));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping("cart")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewCart(@RequestBody CartRequestDTO cart) {
		cartServices.addNewCart(cart);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Thao tác thực hiện thành công!", ""));

	}

	@PutMapping("cart/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateAmount(@PathVariable long id, @RequestParam int amount) {
		try {
			cartServices.updateAmount(id, amount);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	
	@DeleteMapping("cart/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteCartDetail(@PathVariable long id) {
		try {
			cartServices.deleteCartDetail(id);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	@PostMapping("carts/pay")
	@ResponseBody
	public ResponseEntity<ResponseObject> setCartDetailStatus( @RequestBody Long[] cartIds) {
		try {
			cartServices.setItemStatus(cartIds);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	
	@GetMapping("carts/pay/{userId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> setCartDetailStatus(@PathVariable Long userId) {
		try {
			List<CartResponseDTO> cartRequestDTOs = cartServices.getCartSelectedToPay(userId);
			if (cartRequestDTOs != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", cartRequestDTOs));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	
}
