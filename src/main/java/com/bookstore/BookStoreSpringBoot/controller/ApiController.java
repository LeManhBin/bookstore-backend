package com.bookstore.BookStoreSpringBoot.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import com.bookstore.BookStoreSpringBoot.dto.request.Account;
import com.bookstore.BookStoreSpringBoot.dto.request.BookRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.CartRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.OrderRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.PasswordRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.PromotionRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.ReviewRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.StoreRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.request.UserRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookBasicInfoDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookExtendInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookFullInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.CartResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.OrderResponseForStore;
import com.bookstore.BookStoreSpringBoot.dto.response.ReviewResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.ServicePackResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.StoreResponseDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserBasicInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.UserFullInforDTO;
import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.entity.CategoryEntity;
import com.bookstore.BookStoreSpringBoot.entity.ContactEntity;
import com.bookstore.BookStoreSpringBoot.entity.PromotionEntity;
import com.bookstore.BookStoreSpringBoot.entity.ServicePackEntity;
import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;
import com.bookstore.BookStoreSpringBoot.entity.TagEntity;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.object.EmailProvider;
import com.bookstore.BookStoreSpringBoot.object.JwtProvider;
import com.bookstore.BookStoreSpringBoot.object.ResponseObject;
import com.bookstore.BookStoreSpringBoot.services.BookServices;
import com.bookstore.BookStoreSpringBoot.services.CartServices;
import com.bookstore.BookStoreSpringBoot.services.CategoryServices;
import com.bookstore.BookStoreSpringBoot.services.ContactServices;
import com.bookstore.BookStoreSpringBoot.services.OrderServices;
import com.bookstore.BookStoreSpringBoot.services.PromotionServices;
import com.bookstore.BookStoreSpringBoot.services.ReviewServices;
import com.bookstore.BookStoreSpringBoot.services.ServicesPackServices;
import com.bookstore.BookStoreSpringBoot.services.StorageServices;
import com.bookstore.BookStoreSpringBoot.services.StoreServices;
import com.bookstore.BookStoreSpringBoot.services.TagServices;
import com.bookstore.BookStoreSpringBoot.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/BookStore/api")
public class ApiController {

	@Autowired
	EmailProvider emailProvider;
	@Autowired
	CategoryServices categoryServices;
	@Autowired
	UserServices userServices;
	@Autowired
	ServicesPackServices servicesPackServices;
	@Autowired
	StoreServices storeServices;
	@Autowired
	TagServices tagServices;
	@Autowired
	ContactServices contactServices;
	@Autowired
	PromotionServices promotionServices;
	@Autowired
	BookServices bookServices;
	@Autowired
	CartServices cartServices;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	ReviewServices reviewServices;
	@Autowired
	StorageServices storageServices;
	@Autowired
	OrderServices orderServices;

	@PostMapping(value = "/register")
	@ResponseBody
	public ResponseEntity<ResponseObject> registerAccount(@RequestBody UserRequestDTO account) {
		try {
			userServices.addNewUser(account, null);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Lỗi đăng ký tài khoản!", ""));
		}
	}
	@PutMapping(value = "/password/update/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updatePassword(@PathVariable long id, @RequestBody PasswordRequestDTO pasword) {
		try {
			if(userServices.updatePasword(id, pasword))
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(500, "Mật khẩu không chính xác!", false));
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Tài khoản không tồn tại!", ""));
		}
	}
	@PutMapping(value = "/password/reset")
	@ResponseBody
	public ResponseEntity<ResponseObject> resetPassword(@RequestBody PasswordRequestDTO pasword) {
		try {
			if(userServices.resetPassword(pasword))
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(500, "Mật khẩu không chính xác!", false));
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Tài khoản không tồn tại!", ""));
		}
	}
	@GetMapping(value = "/otp")
	@ResponseBody
	public ResponseEntity<ResponseObject> sendOtp(@RequestParam String email) {
		try {
			int randomPin = (int) (Math.random() * 9000) + 1000;
			String subject = "BookStore Send OTP";
			String body = "Hi baby, Your OTP in BookStore is: " + randomPin + ".";
			emailProvider.sendEmail(email, subject, body);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Đã gửi otp qua email!", randomPin));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<ResponseObject> login(@RequestBody Account account) {
		try {
			UserBasicInforDTO user = userServices.checkLogIn(account.getEmail(), account.getPassword());
			Map<String, Object> response = new HashMap<String, Object>();
			if (user != null) {
				String accessToken = jwtProvider.createToken(account.getEmail());
				response.put("user", user);
				response.put("accessToken", accessToken);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Tài khoản, mật khẩu hợp lệ", response));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Tài khoản hoặc mật khẩu không chính xác!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	}

	/***********************************************
	 ********** API FOR CATEGORY ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "category")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllCategory() {
		List<CategoryEntity> categories = categoryServices.getAllCategories();
		if (categories!= null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", categories));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
		}
	}

	@GetMapping(value = "category/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getCategoryByID(@PathVariable long id) {
		CategoryEntity category = categoryServices.getCategoriesByID(id);
		if (category != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", category));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy danh mục id: " + id, ""));
		}
	}

	@PutMapping(value = "category/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateCategory(@PathVariable long id, @RequestBody CategoryEntity category) {
		CategoryEntity categoryEntity = categoryServices.updateCategory(id, category);
		if (categoryEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", categoryEntity));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}

	@PostMapping(value = "category")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewCategory(@RequestBody CategoryEntity category) {
		CategoryEntity categoryEntity = categoryServices.addNewCategory(category);
		if (categoryEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", categoryEntity));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}

	@DeleteMapping(value = "category/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteCategory(@PathVariable long id) {
		CategoryEntity categoryEntity = categoryServices.deleteCategory(id);
		if (categoryEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", categoryEntity));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Danh sách rỗng", ""));

	}

	/***********************************************
	 ********** ****************
	 **********************************************
	 **********************************************/
	@GetMapping(value = "tag")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllTag() {
		List<TagEntity> tagEntities = tagServices.getALlTag();
		if (tagEntities!= null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", tagEntities));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
	}

	@GetMapping(value = "tag/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getTagByID(@PathVariable long id) {
		TagEntity tagEntity = tagServices.getTagByID(id);
		if (tagEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", tagEntity));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
	}

	@PutMapping(value = "tag/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateTag(@PathVariable long id, @RequestBody TagEntity tag) {
		TagEntity tagEntity = tagServices.updateTag(id, tag);
		if (tagEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", tagEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}

	@PostMapping(value = "tag")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewTag(@RequestBody TagEntity tag) {
		TagEntity tagEntity = tagServices.addNewTag(tag);
		if (tagEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", tagEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}

	@DeleteMapping(value = "tag/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteTag(@PathVariable long id) {
		TagEntity tagEntity = tagServices.deleteTag(id);
		if (tagEntity == null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", tagEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}

	/***********************************************
	 ********** API FOR USER ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "user")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllUser() {
		try {
			List<UserBasicInforDTO> userBasicInforDTOs = userServices.getAllUser();
			if (userBasicInforDTOs!= null)
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userBasicInforDTOs));
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}

	}

	@GetMapping(value = "user/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getUserByID(@PathVariable long id) {
		try {
			UserFullInforDTO userFullInforDTO = userServices.getUserByID(id);
			if (userFullInforDTO != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userFullInforDTO));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(505, "Thao tác không được thực hiện!", ""));
		}
	}

	@GetMapping(value = "user/search")
	@ResponseBody
	public ResponseEntity<ResponseObject> findUserByEmail(@RequestParam String key) {
		try {
			UserBasicInforDTO userBasicInforDTO = userServices.findUserByEmail(key);
			if (userBasicInforDTO != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userBasicInforDTO));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(505, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping("/user/{id}")
	public ResponseEntity<ResponseObject> udpateUser(@PathVariable long id, @RequestParam("object") String object,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserRequestDTO user = null;
			user = mapper.readValue(object, UserRequestDTO.class);
			UserEntity userEntity = userServices.updateUser(id, user, file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userEntity));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping("/user")
	public ResponseEntity<ResponseObject> addNewUser(@RequestParam("object") String object,
			@RequestParam(name = "file", required = false) MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserRequestDTO user = null;
			user = mapper.readValue(object, UserRequestDTO.class);
			UserEntity userEntity = userServices.addNewUser(user, file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userEntity));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@DeleteMapping(value = "user/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteUser(@PathVariable long id) {
		UserEntity userEntity = userServices.deleteUser(id);
		if (userEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}

	/***********************************************
	 ********** API FOR SERVICE ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "service")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllServices() {
		try {
			List<ServicePackResponseDTO> servicePackEntities = servicesPackServices.getAllServices();
			if (servicePackEntities!= null)
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", servicePackEntities));
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	}

	@GetMapping(value = "service/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getServicerByID(@PathVariable long id) {
		try {
			ServicePackResponseDTO servicePackEntity = servicesPackServices.getServicesByID(id);
			if (servicePackEntity != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", servicePackEntity));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping("/service")
	public ResponseEntity<ResponseObject> addNewServicePack(@RequestParam("object") String object,
			@RequestParam("file") MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ServicePackEntity servicePack = mapper.readValue(object, ServicePackEntity.class);
			ServicePackEntity servicePackEntity = servicesPackServices.addNewService(servicePack, file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", servicePackEntity));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}

	}

	@PostMapping("/service/{id}")
	public ResponseEntity<ResponseObject> updateServicePack(@PathVariable long id,
			@RequestParam("object") String object, @RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ServicePackEntity servicePack = mapper.readValue(object, ServicePackEntity.class);
			servicesPackServices.updateService(id, servicePack, file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}

	@DeleteMapping(value = "service/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteServicePack(@PathVariable long id) {
		ServicePackEntity servicePackEntity = servicesPackServices.deleteService(id);
		if (servicePackEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
	}

	/***********************************************
	 ********** API FOR STORE ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "store")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllStore() {
		try {
			List<StoreResponseDTO> storeResponses = storeServices.getAllStore();
			if (storeResponses!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", storeResponses));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}

	}

	@GetMapping(value = "store/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getStoreByID(@PathVariable long id) throws IOException {
		try {
			StoreResponseDTO store = storeServices.getStoreByID(id);
			if (store != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", store));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	}

	@PostMapping("store")
	public ResponseEntity<ResponseObject> addNewStore(@RequestParam("object") String object,
			@RequestParam(name = "avatar", required = false) MultipartFile avatar,
			@RequestParam(name = "coverimage", required = false) MultipartFile coverimage) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			StoreRequestDTO store = mapper.readValue(object, StoreRequestDTO.class);
			storeServices.addNewStore(store, avatar, coverimage);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));

		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}

	}

	@PostMapping("store/{id}")
	public ResponseEntity<ResponseObject> udpateStore(@PathVariable long id, @RequestParam("object") String object,
			@RequestParam(name = "avatar", required = false) MultipartFile avatar,
			@RequestParam(name = "coverimage", required = false) MultipartFile coverimage) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			StoreRequestDTO store = mapper.readValue(object, StoreRequestDTO.class);
			storeServices.udpateStore(id, store, avatar, coverimage);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));

		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}

	@DeleteMapping(value = "store/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteStore(@PathVariable long id) {
		StoreEntity storeEntity = storeServices.deleteStore(id);
		if (storeEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
	}

	/***********************************************
	 ********** API FOR CONTACT ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "contact")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllContact() {
		List<ContactEntity> contactEntities = contactServices.getAllContact();
		if (contactEntities!= null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", contactEntities));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
	}

	@GetMapping(value = "contact/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getContactByID(@PathVariable long id) {
		ContactEntity contactEntity = contactServices.getContactByID(id);
		if (contactEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", contactEntity));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Danh sách rỗng!", ""));
	}

	@PostMapping(value = "contact")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewContact(@RequestBody ContactEntity contact) {
		ContactEntity contactEntity = contactServices.addNewContact(contact);
		if (contactEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", contactEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));

	}

	@DeleteMapping(value = "contact/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteContact(@PathVariable long id) {
		ContactEntity contactEntity = contactServices.deleteContact(id);
		if (contactEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", contactEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}

	@PostMapping(value = "contact/reply")
	@ResponseBody
	public ResponseEntity<ResponseObject> replyContact(@RequestBody ContactEntity contact) {
		try {
			emailProvider.sendEmail(contact.getGmail(), contact.getSubject(), contact.getContent());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", ""));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping(value = "/checkemail")
	@ResponseBody
	public ResponseEntity<ResponseObject> checkEmail(@RequestBody Account account) {
		try {
			UserBasicInforDTO userResponse = userServices.findUserByEmail(account.getEmail());
			if (userResponse != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Tài khoản đã tồn tại, vui lòng chọn Đăng nhập!", account));
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Tài khoản hợp lệ!", null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", null));
		}
	}

	/***********************************************
	 ********** API FOR BOOK
	 * 
	 * @throws IOException ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "book/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getBookByID(@PathVariable long id) {
		try {
			BookFullInforDTO bookEntity = bookServices.getBookByID(id);
			if (bookEntity != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thành công!", bookEntity));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}

	}

	@GetMapping(value = "book/search/{key}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getBookByID(@PathVariable String key) {
		try {
			List<BookBasicInfoDTO> bookBasicInfoDTOs = bookServices.getBookByKeyword(key);
			if (bookBasicInfoDTOs!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookBasicInfoDTOs));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	}

	@GetMapping(value = "books")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllBook() {
		try {
			List<BookBasicInfoDTO> bookBasicInfoDTOs = bookServices.getAllBook();
			if (bookBasicInfoDTOs!= null)
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookBasicInfoDTOs));
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@GetMapping(value = "book/related/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getListRelatedBook(@PathVariable long id) {
		try {
			List<BookBasicInfoDTO> relatedBookEntities = bookServices.getListRelatedBooks(id);
			if (relatedBookEntities!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", relatedBookEntities));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@GetMapping(value = "books/{storeId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllBookByStoreID(@PathVariable Long storeId) throws IOException {
		try {
			List<BookBasicInfoDTO> bookStoreEntities = bookServices.getBooksByStoreId(storeId);
			if (bookStoreEntities!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookStoreEntities));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@GetMapping(value = "books/{storeId}/{status}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllBookByStoreIDAndStatus(@PathVariable Long storeId,
			@PathVariable int status) {
		try {
			List<BookExtendInforDTO> bookStoreEntities;
			bookStoreEntities = bookServices.getAllBookByStoreIDAndStatus(storeId, status);
			if (bookStoreEntities!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookStoreEntities));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping(value = "book")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewBook(@RequestParam(name = "object") String object,
			@RequestParam("files") MultipartFile[] files) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			BookRequestDTO book = mapper.readValue(object, BookRequestDTO.class);
			bookServices.addNewBook(book, files);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping(value = "book/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateBook(@PathVariable Long id,
			@RequestParam(name = "object") String object,
			@RequestParam(name = "files", required = false) MultipartFile[] files) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			BookRequestDTO book = mapper.readValue(object, BookRequestDTO.class);
			BookEntity bookEntity = bookServices.updateBook(id, book, files);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookEntity));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@DeleteMapping(value = "book/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteBook(@PathVariable long id) {
		BookEntity bookEntity = bookServices.setBookStatus(id, 2);
		if (bookEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}

	/***********************************************
	 ********** API FOR PROMOTION ***********
	 **********************************************
	 **********************************************/

	@GetMapping(value = "promotions/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllPromotionByStoreID(@PathVariable long id) {
		List<PromotionEntity> promotionEntities;
		promotionEntities = promotionServices.getAllPromotionByStoreId(id);
		if (promotionEntities!= null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntities));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		}
	}
	@GetMapping(value = "promotions/book/{storeId}")
	@ResponseBody
	public ResponseEntity<ResponseObject>  getProductForPromotion(@PathVariable("storeId") long storeId, @RequestParam("startDate") Date startDate,  @RequestParam("endDate") Date endDate) {
		List<BookBasicInfoDTO> books = bookServices.getBookForAddNewPromotion(storeId,startDate, endDate);
		if(books != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", books));
		}else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		
	}
	@GetMapping(value = "promotions/book/{storeId}/{promotionId}")
	@ResponseBody
	public ResponseEntity<ResponseObject>  getProductForUpdatePromotion(@PathVariable("storeId") long storeId, 
			@PathVariable("promotionId") long promotionId){
		List<BookBasicInfoDTO> books = bookServices.getBookForUpdatePromotion(storeId, promotionId);
		if(books != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", books));
		}else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		
	}
	@GetMapping(value = "promotion/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getPromotionByID(@PathVariable long id) {
		PromotionEntity promotionEntity = promotionServices.getPromotionById(id);
		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		}
	}

	@PostMapping(value = "promotion")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewPromotion(@RequestBody PromotionRequestDTO promotion) {
		PromotionEntity promotionEntity = promotionServices.addNewPromotion(promotion);
		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@DeleteMapping(value = "promotion/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deletePromotion(@PathVariable long id) {
		PromotionEntity promotionEntity = promotionServices.deletePromotion(id);
		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PutMapping(value = "promotion/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updatePromotion(@PathVariable Long id,
			@RequestBody PromotionRequestDTO promotion) {

		PromotionEntity promotionEntity = promotionServices.updatePromotion(id, promotion);

		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}

	}

	@PutMapping(value = "promotion/{id}/{status}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updatePromotionStatus(@PathVariable Long id, @PathVariable int status) {
		PromotionEntity promotionEntity = promotionServices.updatePromotionStatus(id, status);
		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}


	/***********************************************
	 ********** API FOR CART ***********
	 **********************************************
	 **********************************************/
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
	
	/***********************************************
	 ********** API FOR ORDER ***********
	 **********************************************
	 **********************************************/
	@GetMapping("store/orders/{storeId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getOrdersByStoreId( @PathVariable long storeId) {
		try {
			List<OrderResponseForStore> orders = orderServices.getOrdersByStoreId(storeId);
			if(orders != null)
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", orders));
			else 
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", orders));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	@GetMapping("store/order/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getOrdersById( @PathVariable long id) {
		try {
			OrderResponseForStore orders = orderServices.getOrdersById(id);
			if(orders != null)
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", orders));
			else 
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", orders));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	@PostMapping("order")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewOrder( @RequestBody OrderRequestDTO order) {
		try {
			orderServices.addNewOrder(order);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	
	@GetMapping("order/{userId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getOrders( @PathVariable long userId) {
		try {
			List<OrderResponseDTO> orders = orderServices.getOrderByUserId(userId);
			if(orders != null)
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", orders));
			else
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	@PutMapping("order/{id}/{status}")
	@ResponseBody
	public ResponseEntity<ResponseObject> udpateOrderStatus(@PathVariable long id, @PathVariable int status) {
		try {
			orderServices.updateOrderStatus(id, status);
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	

	
	/***********************************************
	 ********** API FOR REVIEW ***********
	 **********************************************
	 **********************************************/
	@GetMapping("/book/review/{bookId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getReviewForBook(@PathVariable("bookId") long id) {
		try {
			List<ReviewResponseDTO> reviews = reviewServices.getAllReviewByBookId(id);
			if(reviews != null)
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", reviews));
			else 
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	@PostMapping("/review")
	@ResponseBody
	public ResponseEntity<ResponseObject> addReview(@RequestBody ReviewRequestDTO review) {
		try {
			reviewServices.addNewReview(review);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	
	
}
