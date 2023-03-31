package com.bookstore.BookStoreSpringBoot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
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

import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.entity.CartEntity;
import com.bookstore.BookStoreSpringBoot.entity.CategoryEntity;
import com.bookstore.BookStoreSpringBoot.entity.ContactEntity;
import com.bookstore.BookStoreSpringBoot.entity.PromotionEntity;
import com.bookstore.BookStoreSpringBoot.entity.ReviewEntity;
import com.bookstore.BookStoreSpringBoot.entity.ServicePackEntity;
import com.bookstore.BookStoreSpringBoot.entity.StoreEntity;
import com.bookstore.BookStoreSpringBoot.entity.TagEntity;
import com.bookstore.BookStoreSpringBoot.entity.UserEntity;
import com.bookstore.BookStoreSpringBoot.object.Book;
import com.bookstore.BookStoreSpringBoot.object.Cart;
import com.bookstore.BookStoreSpringBoot.object.Category;
import com.bookstore.BookStoreSpringBoot.object.Contact;
import com.bookstore.BookStoreSpringBoot.object.EmailProvider;
import com.bookstore.BookStoreSpringBoot.object.JwtProvider;
import com.bookstore.BookStoreSpringBoot.object.ObjectWithFile;
import com.bookstore.BookStoreSpringBoot.object.Promotion;
import com.bookstore.BookStoreSpringBoot.object.ResponseObject;
import com.bookstore.BookStoreSpringBoot.object.Review;
import com.bookstore.BookStoreSpringBoot.object.ServicePack;
import com.bookstore.BookStoreSpringBoot.object.Store;
import com.bookstore.BookStoreSpringBoot.object.Tag;
import com.bookstore.BookStoreSpringBoot.object.User;
import com.bookstore.BookStoreSpringBoot.services.BookServices;
import com.bookstore.BookStoreSpringBoot.services.CartServices;
import com.bookstore.BookStoreSpringBoot.services.CategoryServices;
import com.bookstore.BookStoreSpringBoot.services.ContactServices;
import com.bookstore.BookStoreSpringBoot.services.PromotionServices;
import com.bookstore.BookStoreSpringBoot.services.ReviewServices;
import com.bookstore.BookStoreSpringBoot.services.ServicesPackServices;
import com.bookstore.BookStoreSpringBoot.services.StoreServices;
import com.bookstore.BookStoreSpringBoot.services.TagServices;
import com.bookstore.BookStoreSpringBoot.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;


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
	ServletContext context;
	
	private static final String UPLOAD_DIRECTORY = "/image";
	
	@PostMapping(value = "/register")
	@ResponseBody
	public ResponseEntity<ResponseObject> registerAccount(@RequestBody User account) {
		UserEntity userEntity = userServices.addNewUser(account);
		if (userEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!", userEntity));
		}else
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Lỗi đăng ký tài khoản!", ""));
	}

	@GetMapping(value = "/otp")
	@ResponseBody
	public ResponseEntity<ResponseObject> sendOtp(@RequestParam String email) {
		int randomPin = (int) (Math.random() * 9000) + 1000;
		String subject = "BookStore Send OTP";
		String body = "Hi baby, Your OTP to create Account in BookStore is: " + randomPin + ".";
		if(	emailProvider.sendEmail(email, subject, body)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Đã gửi otp qua email!", randomPin));
		}else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!", ""));
		}
	}
	@PostMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<ResponseObject> login(@RequestBody User account) {
		UserEntity userEntity = userServices.checkLogIn(account.getEmail(), account.getPassword());
		Map<String, Object> response = new HashMap<String, Object>();
		if (userEntity != null) {
			String accessToken = jwtProvider.createToken(account.getEmail());
			response.put("user", userEntity);
			response.put("accessToken", accessToken);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Tài khoản, mật khẩu hợp lệ", response));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Tài khoản hoặc mật khẩu không chính xác!", ""));
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
		if(categories.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công", categories));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Danh sách trống!", ""));
		}
	}

	@GetMapping(value = "category/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getCategoryByID(@PathVariable long id) {
		CategoryEntity category = categoryServices.getCategoriesByID(id);
		if(category != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công", category));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Không tìm thấy danh mục id: "+id, ""));
		}
	}

	@PutMapping(value = "category/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateCategory(@PathVariable long id, @RequestBody Category category) {
		CategoryEntity categoryEntity = categoryServices.updateCategory(id, category);
		if(categoryEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công", categoryEntity));
		}
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!", ""));
	}

	@PostMapping(value = "category")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewCategory(@RequestBody Category category) {
		CategoryEntity categoryEntity = categoryServices.addNewCategory(category);
		if(categoryEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công", categoryEntity));
		}
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!", ""));
	}

	@DeleteMapping(value = "category/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteCategory(@PathVariable long id) {
		CategoryEntity categoryEntity = categoryServices.deleteCategory(id);
		if(categoryEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công", categoryEntity));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Danh sách rỗng", ""));
		
	}

	/***********************************************
	 ********** ****************
	 **********************************************
	 **********************************************/
	@GetMapping(value = "tag")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllTag() {
		List<TagEntity> tagEntities = tagServices.getALlTag();
		if(tagEntities.size() > 0)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",tagEntities));
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Danh sách rỗng!",""));
	}

	@GetMapping(value = "tag/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getTagByID(@PathVariable long id) {
		TagEntity tagEntity = tagServices.getTagByID(id);
		if(tagEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",tagEntity));
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Không tìm thấy dữ liệu!",""));
	}

	@PutMapping(value = "tag/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateTag(@PathVariable long id, @RequestBody Tag tag) {
		TagEntity tagEntity = tagServices.updateTag(id, tag);
		if(tagEntity !=  null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",tagEntity));
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	@PostMapping(value = "tag")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewTag(@RequestBody Tag tag) {
		TagEntity tagEntity = tagServices.addNewTag(tag);
		if(tagEntity !=  null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",tagEntity));
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}
	
	@DeleteMapping(value = "tag/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteTag(@PathVariable long id) {
		TagEntity tagEntity = tagServices.deleteTag(id);
		if(tagEntity !=  null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",tagEntity));
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	/***********************************************
	 ********** API FOR USER ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "user")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllUser() throws IOException {
		List<UserEntity> users = userServices.getAllUser();
		List<ObjectWithFile<UserEntity>> userDTOs = new ArrayList<ObjectWithFile<UserEntity>>();
		for (UserEntity user : users) {
			ObjectWithFile<UserEntity> userEnity;
			if (user.getAvatar() != null) {
				Path imagePath = Paths.get(user.getAvatar());
				ArrayList<byte[]> images = new ArrayList<byte[]>();
				byte[] imageBytes = Files.readAllBytes(imagePath);
				images.add(imageBytes);
				userEnity = new ObjectWithFile<UserEntity>(user, images);
			} else
				userEnity = new ObjectWithFile<UserEntity>(user);
			userDTOs.add(userEnity);
		}
		if(userDTOs.size() > 0)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",userDTOs));
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Danh sách rỗng!",""));
	}

	@GetMapping(value = "user/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getUserByID(@PathVariable long id) throws IOException {
		UserEntity userEntity = userServices.getUserByID(id);
		if (userEntity != null) {
			ObjectWithFile<UserEntity> user;
			if(userEntity.getAvatar() != null) {
				Path imagePath = Paths.get(userEntity.getAvatar());
				ArrayList<byte[]> images = new ArrayList<byte[]>();
				byte[] imageBytes = Files.readAllBytes(imagePath);
				images.add(imageBytes);
				user = new ObjectWithFile<UserEntity>(userEntity, images);
			}else {
				user = new ObjectWithFile<UserEntity>(userEntity, null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",user));
		} else
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Không tìm thấy user!",""));
	}

	@GetMapping(value = "user/search")
	@ResponseBody
	public ResponseEntity<ResponseObject> findUserByEmailOrPhoneNumber(@RequestParam String key) {
		UserEntity users = userServices.findUserByEmail(key);
		if(users != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",users));
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Không tìm thấy user!",""));
	}
	@PostMapping("/user/{id}")
	public ResponseEntity<ResponseObject> udpateUser(@PathVariable long id, @RequestParam("object") String object,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user = mapper.readValue(object, User.class);
			if (file != null) {
				String filename = file.getOriginalFilename();
				context = session.getServletContext();
				String path = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + filename;
				user.setAvatar(path);
				FileCopyUtils.copy(file.getBytes(), new File(path));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		UserEntity userEntity = userServices.updateUser(id, user);
		if(userEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",userEntity));
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	@PostMapping("/user")
	public ResponseEntity<ResponseObject> addNewUser(@RequestParam("object") String object,
			@RequestParam(name="file", required = false) MultipartFile file, HttpSession session) {
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user = mapper.readValue(object, User.class);
			if(file != null) {
				String filename = file.getOriginalFilename();
				ServletContext context = session.getServletContext();
				String path = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + filename;
				System.out.println("======================================"+path);
				user.setAvatar(path);
				FileCopyUtils.copy(file.getBytes(), new File(path));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserEntity userEntity = userServices.addNewUser(user);
		if(userEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",userEntity));
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	@DeleteMapping(value = "user/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteUser(@PathVariable long id) {
		UserEntity userEntity = userServices.deleteUser(id);
		if(userEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",userEntity));
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	/***********************************************
	 ********** API FOR SERVICE ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "service")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllServices() throws IOException {
		List<ServicePackEntity> servicePackEntities = servicesPackServices.getAllServices();
		List<ObjectWithFile<ServicePackEntity>> services = new ArrayList<ObjectWithFile<ServicePackEntity>>();
		for (ServicePackEntity servicePackEntitie : servicePackEntities) {
			ObjectWithFile<ServicePackEntity> service;
			if (servicePackEntitie.getThumbnail() != null) {
				Path imagePath = Paths.get(servicePackEntitie.getThumbnail());
				ArrayList<byte[]> images = new ArrayList<byte[]>();
				byte[] imageBytes = Files.readAllBytes(imagePath);
				images.add(imageBytes);
				service = new ObjectWithFile<ServicePackEntity>(servicePackEntitie, images);
			} else
				service = new ObjectWithFile<ServicePackEntity>(servicePackEntitie);
			services.add(service);
		}
		if(services.size() > 0)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",services));
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Danh sách rỗng!",""));
	}

	@GetMapping(value = "service/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getServicerByID(@PathVariable long id) throws IOException {
		ServicePackEntity servicePackEntity = servicesPackServices.getServicesByID(id);
		if (servicePackEntity != null) {
			ObjectWithFile<ServicePackEntity> service;
			if(servicePackEntity.getThumbnail() != null) {
				Path imagePath = Paths.get(servicePackEntity.getThumbnail());
				ArrayList<byte[]> images = new ArrayList<byte[]>();
				byte[] imageBytes = Files.readAllBytes(imagePath);
				images.add(imageBytes);
				service = new ObjectWithFile<ServicePackEntity>(servicePackEntity,
						images);
			}else {
				service = new ObjectWithFile<ServicePackEntity>(servicePackEntity);
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",service));
		} else
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Danh sách rỗng!",""));
	}

	@PostMapping("/service")
	public ResponseEntity<ResponseObject> addNewServicePack(@RequestParam("object") String object,
			@RequestParam("file") MultipartFile file, HttpSession session) {
		ObjectMapper mapper = new ObjectMapper();
		ServicePack servicePack = null;
		try {
			servicePack = mapper.readValue(object, ServicePack.class);
			String filename = file.getOriginalFilename();
			ServletContext context = session.getServletContext();
			String path = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + filename;
			servicePack.setThumbnail(path);
			FileCopyUtils.copy(file.getBytes(), new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServicePackEntity servicePackEntity = servicesPackServices.addNewService(servicePack);
		if(servicePackEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",servicePackEntity));
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	@PostMapping("/service/{id}")
	public ResponseEntity<ResponseObject> updateServicePack(@PathVariable long id,
			@RequestParam("object") String object, @RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		ServicePackEntity servicePackEntityResponse = null;
		ObjectMapper mapper = new ObjectMapper();
		ServicePack servicePack = null;
		try {
			servicePack = mapper.readValue(object, ServicePack.class);
			if (file != null) {
				String filename = file.getOriginalFilename();
				ServletContext context = session.getServletContext();
				String path = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + filename;
				servicePack.setThumbnail(path);
				FileCopyUtils.copy(file.getBytes(), new File(path));
			}
			servicePackEntityResponse = servicesPackServices.updateService(id, servicePack);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(servicePackEntityResponse != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",servicePackEntityResponse));
		}
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	@DeleteMapping(value = "service/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteServicePack(@PathVariable long id) {
		ServicePackEntity servicePackEntity = servicesPackServices.deleteService(id);
		if(servicePackEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",servicePackEntity));
		}
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	/***********************************************
	 ********** API FOR STORE ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "store")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllStore() throws IOException {
		List<StoreEntity> storeEntities = storeServices.getAllStore();
		List<ObjectWithFile<StoreEntity>> stores = new ArrayList<ObjectWithFile<StoreEntity>>();
		for (StoreEntity storeEntitie : storeEntities) {
			ObjectWithFile<StoreEntity> store;
			ArrayList<byte[]> images = new ArrayList<byte[]>();
			if(storeEntitie.getAvatar() != null) {
				Path avatarPath = Paths.get(storeEntitie.getAvatar());
				byte[] imaavatarBytes = Files.readAllBytes(avatarPath);
				images.add(imaavatarBytes);
			}else {
				images.add(null);
			}
			if(storeEntitie.getCoverImage() != null) {
				Path coverImagePath = Paths.get(storeEntitie.getCoverImage());
				byte[] coverImageBytes = Files.readAllBytes(coverImagePath);
				images.add(coverImageBytes);
			}else {
				images.add(null);
			}
			store = new ObjectWithFile<StoreEntity>(storeEntitie, images);
			stores.add(store);
		}
		if(stores.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",stores));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Danh sách rỗng",""));
		}
		
	}

	@GetMapping(value = "store/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getStoreByID(@PathVariable long id) throws IOException {
		StoreEntity storeEntity = storeServices.getStoreByID(id);
		if (storeEntity != null) {
			ArrayList<byte[]> images = new ArrayList<byte[]>();
			if(storeEntity.getAvatar() != null) {
				Path avatarPath = Paths.get(storeEntity.getAvatar());
				byte[] avatarBytes = Files.readAllBytes(avatarPath);
				images.add(avatarBytes);
			}else {
				images.add(null);
			}
			if(storeEntity.getCoverImage() != null) {
				Path coverImagePath = Paths.get(storeEntity.getCoverImage());
				byte[] coverImageBytes = Files.readAllBytes(coverImagePath);
				images.add(coverImageBytes);
			}else {
				images.add(null);
			}
			ObjectWithFile<StoreEntity> store = new ObjectWithFile<StoreEntity>(storeEntity, images);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",store));
		} else
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Không tìm thấy dữ liệu!",""));
	}

	@PostMapping("store")
	public ResponseEntity<ResponseObject> addNewStore(@RequestParam("object") String object,
			@RequestParam(name="avatar", required = false) MultipartFile avatar, @RequestParam(name="coverimage", required = false) MultipartFile coverimage,
			HttpSession session) {
		ObjectMapper mapper = new ObjectMapper();
		Store store = null;
		try {
			store = mapper.readValue(object, Store.class);
			ServletContext context = session.getServletContext();
			if(avatar != null) {
				String avatarName = avatar.getOriginalFilename();
				String avatarPath = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + avatarName;
				store.setAvatar(avatarPath);
				FileCopyUtils.copy(avatar.getBytes(), new File(avatarPath));
			}
			if(coverimage != null) {
				String coverImageName = coverimage.getOriginalFilename();
				String coverImagePath = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + coverImageName;
				store.setCoverImage(coverImagePath);
				FileCopyUtils.copy(coverimage.getBytes(), new File(coverImagePath));
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		StoreEntity storeEntity = storeServices.addNewStore(store);
		if(storeEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!", storeEntity));
		}
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}
	@PostMapping("store/{id}")
	public ResponseEntity<ResponseObject> udpateStore(@PathVariable long id, @RequestParam("object") String object,
			@RequestParam(name = "avatar", required = false) MultipartFile avatar,
			@RequestParam(name = "coverimage", required = false) MultipartFile coverimage, HttpSession session) {
		ObjectMapper mapper = new ObjectMapper();
		Store store = null;
		try {
			store = mapper.readValue(object, Store.class);
			if (avatar != null) {
				String avatarName = avatar.getOriginalFilename();
				ServletContext context = session.getServletContext();
				String avatarPath = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + avatarName;
				store.setAvatar(avatarPath);
				FileCopyUtils.copy(avatar.getBytes(), new File(avatarPath));
			}
			if (coverimage != null) {
				String coverImageName = coverimage.getOriginalFilename();
				ServletContext context = session.getServletContext();
				String coverImagePath = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + coverImageName;
				store.setCoverImage(coverImagePath);
				FileCopyUtils.copy(coverimage.getBytes(), new File(coverImagePath));
			}
			StoreEntity storeEntity = storeServices.udpateStore(id, store);
			if(storeEntity != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",storeEntity));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	@DeleteMapping(value = "store/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteStore(@PathVariable long id) {
		StoreEntity storeEntity = storeServices.deleteStore(id);
		if(storeEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",storeEntity));
		}
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	/***********************************************
	 ********** API FOR CONTACT ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "contact")
	@ResponseBody
	public ResponseEntity<ResponseObject> getContactByType(@RequestParam(required = false) String type) {
		List<ContactEntity> contactEntities;
		if (type == null)
			contactEntities = contactServices.getAllContact(1);
		else
			contactEntities = contactServices.getAllContact(0);
		if(contactEntities.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",contactEntities));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Danh sách rỗng!",""));
	}

	@GetMapping(value = "contact/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getContactByID(@PathVariable long id) {
		ContactEntity contactEntity = contactServices.getContactByID(id);
		if(contactEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",contactEntity));
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Danh sách rỗng!",""));
	}

	@PostMapping(value = "contact")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewContact(@RequestBody Contact contact) {
		ContactEntity contactEntity = contactServices.addNewContact(contact);
		if(contactEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",contactEntity));
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	
	}

	@DeleteMapping(value = "contact/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteContact(@PathVariable long id) {
		ContactEntity contactEntity = contactServices.deleteContact(id);
		if(contactEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",contactEntity));
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}

	@PostMapping(value = "contact/reply")
	@ResponseBody
	public ResponseEntity<ResponseObject> replyContact(@RequestBody Contact contact) {
		boolean check = emailProvider.sendEmail(contact.getGmail(), contact.getSubject(), contact.getContent());
		if(check) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",check));
		}else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",check));
		}
	}

	@PostMapping(value = "/checkemail")
	@ResponseBody
	public ResponseEntity<ResponseObject> checkEmail(@RequestBody User account) {
		UserEntity userEntities = userServices.findUserByEmail(account.getEmail());
		if(userEntities != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Tài khoản đã tồn tại, vui lòng chọn Đăng nhập!",userEntities));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Tài khoản hợp lệ!",null));
		}
	}

	/***********************************************
	 ********** API FOR BOOK 
	 * @throws IOException ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "book/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getBookByID(@PathVariable long id) throws IOException {
		BookEntity bookEntity = bookServices.getBookByID(id);
		if(bookEntity != null) {
			ObjectWithFile<BookEntity> book = new ObjectWithFile<BookEntity>();
			if(bookEntity.getImage() != null) {
				String imageNames[] = bookEntity.getImage().split(";");
				ArrayList<byte[]> images = new ArrayList<byte[]>();
				for(String imageString:imageNames) {
					Path imagePath = Paths.get(imageString);
					byte[] imageBytes = Files.readAllBytes(imagePath);
					images.add(imageBytes);
				}
				book = new ObjectWithFile<BookEntity>(bookEntity, images);
			}else
				book = new ObjectWithFile<BookEntity>(bookEntity, null);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thành công!",book));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Không tìm thấy dữ liệu!",""));
		}
		
	}

	@GetMapping(value = "book/related/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getListRelatedBook(@PathVariable long id) throws IOException {
		List<BookEntity> relatedBookEntities = bookServices.getListRelatedBooks(id);
		if(relatedBookEntities.size() > 0) {
			List<ObjectWithFile<BookEntity>> releatedBooks = new ArrayList<ObjectWithFile<BookEntity>>();
			ObjectWithFile<BookEntity> releatedBook;
			for(BookEntity relatedBookEntity:relatedBookEntities) {
				if(relatedBookEntity.getImage() != null) {
					String imageNames[] = relatedBookEntity.getImage().split(";");
					ArrayList<byte[]> images = new ArrayList<byte[]>();
					for(String imageString:imageNames) {
						Path imagePath = Paths.get(imageString);
						byte[] imageBytes = Files.readAllBytes(imagePath);
						images.add(imageBytes);
					}
					releatedBook = new ObjectWithFile<BookEntity>(relatedBookEntity, images);
				}else
					releatedBook = new ObjectWithFile<BookEntity>(relatedBookEntity, null);
				releatedBooks.add(releatedBook);
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",releatedBooks));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Không tìm thấy dữ liệu!",""));
		}
	}

	@GetMapping(value = "books/{storeId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllBookByStoreID(@PathVariable Long storeId) throws IOException {
		List<BookEntity> bookStoreEntities = bookServices.getBooksByStoreId(storeId);
		if(bookStoreEntities.size() > 0) {
			List<ObjectWithFile<BookEntity>> bookStores = new ArrayList<ObjectWithFile<BookEntity>>();
			ObjectWithFile<BookEntity> bookStore;
			for(BookEntity bookEntity:bookStoreEntities) {
				if(bookEntity.getImage() != null) {
					String imageNames[] = bookEntity.getImage().split(";");
					ArrayList<byte[]> images = new ArrayList<byte[]>();
					for(String imageString:imageNames) {
						Path imagePath = Paths.get(imageString);
						byte[] imageBytes = Files.readAllBytes(imagePath);
						images.add(imageBytes);
					}
					bookStore = new ObjectWithFile<BookEntity>(bookEntity, images);
				}else
					bookStore = new ObjectWithFile<BookEntity>(bookEntity, null);
				bookStores.add(bookStore);
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",bookStores));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Không tìm thấy dữ liệu!",""));
		}
	}
	@GetMapping(value = "books/{storeId}/{status}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllBookByStoreIDAndStatus(@PathVariable Long storeId,
			@PathVariable int status) throws IOException {
		List<BookEntity> bookStoreEntities;
		bookStoreEntities = bookServices.getAllBookByStoreIDAndStatus(storeId, status);
		if(bookStoreEntities.size() > 0) {
			List<ObjectWithFile<BookEntity>> bookStores = new ArrayList<ObjectWithFile<BookEntity>>();
			ObjectWithFile<BookEntity> bookStore;
			for(BookEntity bookEntity:bookStoreEntities) {
				if(bookEntity.getImage() != null) {
					String imageNames[] = bookEntity.getImage().split(";");
					ArrayList<byte[]> images = new ArrayList<byte[]>();
					for(String imageString:imageNames) {
						Path imagePath = Paths.get(imageString);
						byte[] imageBytes = Files.readAllBytes(imagePath);
						images.add(imageBytes);
					}
					bookStore = new ObjectWithFile<BookEntity>(bookEntity, images);
				}else
					bookStore = new ObjectWithFile<BookEntity>(bookEntity, null);
				bookStores.add(bookStore);
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",bookStores));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Không tìm thấy dữ liệu!",""));
		}
	}

	@PostMapping(value = "book")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewBook(@RequestParam(name = "object") String object,
			@RequestParam("files") MultipartFile[] files, HttpSession session) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Book book = mapper.readValue(object, Book.class);
		if (files != null) {
			ServletContext context = session.getServletContext();
			String imagePaths = "";
			String path;
			for (MultipartFile file : files) {
				String filename = file.getOriginalFilename();
				path = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + filename;
				FileCopyUtils.copy(file.getBytes(), new File(path));
				imagePaths.concat(path + ";");
			}
			book.setImage(imagePaths);
		}
		BookEntity bookEntity = bookServices.addNewBook(book);
		if(bookEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",bookEntity));
		}else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!", null));
		}
	}

	@PostMapping(value = "book/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateBook(@PathVariable Long id, @RequestParam(name = "object") String object,
			@RequestParam("files") MultipartFile[] files, HttpSession session) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Book book = mapper.readValue(object, Book.class);
		if (files != null) {
			ServletContext context = session.getServletContext();
			String imagePaths = "";
			String path;
			for (MultipartFile file : files) {
				String filename = file.getOriginalFilename();
				path = context.getRealPath(UPLOAD_DIRECTORY) + File.separator + filename;
				FileCopyUtils.copy(file.getBytes(), new File(path));
				imagePaths.concat(path + ";");
			}
			book.setImage(imagePaths);
		}	
		BookEntity bookEntity = bookServices.updateBook(id, book);
		if(bookEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",bookEntity));
		}else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
		}
	}

	@DeleteMapping(value = "book/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteBook(@PathVariable long id) {
		BookEntity bookEntity = bookServices.setBookStatus(id, 0);
		if(bookEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",bookEntity));
		}else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
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
		if(promotionEntities.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",promotionEntities));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Danh sách rỗng!",""));
		}
			
	}

	@GetMapping(value = "promotions/{id}/{status}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllPromotionByStoreIDAndStatus(@PathVariable long id,
			@PathVariable int status) {
		List<PromotionEntity> promotionEntities;
		promotionEntities = promotionServices.getAllPromotionByStoreIdAndStatus(id, status);
		if(promotionEntities.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",promotionEntities));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Danh sách rỗng!",""));
		}
	}

	@GetMapping(value = "promotion/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getPromotionByID(@PathVariable long id) {
		PromotionEntity promotionEntity = promotionServices.getPromotionById(id);
		if(promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",promotionEntity));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Không tìm thấy dữ liệu!",""));
		}
	}

	@PostMapping(value = "promotion")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewPromotion(@RequestBody Promotion promotion) {
		PromotionEntity promotionEntity = promotionServices.addNewPromotion(promotion);
		if(promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",promotionEntity));
		}else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
		}
	}

	@DeleteMapping(value = "promotion/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deletePromotion(@PathVariable long id) {
		PromotionEntity promotionEntity = promotionServices.deletePromotion(id);
		if(promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",promotionEntity));
		}else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
		}
	}

	@PutMapping(value = "promotion/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updatePromotion(@PathVariable Long id, @RequestBody Promotion promotion) {
		
		PromotionEntity promotionEntity = promotionServices.updatePromotion(id, promotion);
		
		if(promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",promotionEntity));
		}else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
		}
		
	}

	@PutMapping(value = "promotion/{id}/{status}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updatePromotionStatus(@PathVariable Long id, @PathVariable int status) {
		PromotionEntity promotionEntity = promotionServices.updatePromotionStatus(id, status);
		if(promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",promotionEntity));
		}else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
		}
	}
	
	/***********************************************
	 ********** API FOR REVIEW ***********
	 **********************************************
	 **********************************************/
	@GetMapping("book/review/{id}")
	public ResponseEntity<ResponseObject> getAllReviewByBookID(@PathVariable long id){
		List<ReviewEntity> reviews = reviewServices.getAllReviewByBookID(id);
		if(reviews.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",reviews));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Danh sách rỗng!",""));
		}
	}
	
	@PostMapping("book/review")
	public ResponseEntity<ResponseObject> addNewReview(@RequestBody Review review){
		ReviewEntity reviewEntity = reviewServices.addNewReview(review);
		if(review != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",reviewEntity));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
		}
	}
	
	
	@GetMapping("carts/{userid}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllCartDetailByUserId(@PathVariable long userid) throws IOException{
		List<CartEntity> cartEntities = cartServices.getAllCartDetailByUserId(userid);
		if(cartEntities.size() > 0){
			List<ObjectWithFile<CartEntity>> cartDetailAndFile = new ArrayList<ObjectWithFile<CartEntity>>();
			ObjectWithFile<CartEntity> cart;
			for(CartEntity cartEntity:cartEntities) {
				String imageBook = cartEntity.getBookEntity().getImage();
				if(imageBook != null) {
					String imageNames[] = imageBook.split(";");
					ArrayList<byte[]> images = new ArrayList<byte[]>();
					for(String imageString:imageNames){
						Path imagePath = Paths.get(imageString);
						byte[] imageBytes = Files.readAllBytes(imagePath);
						images.add(imageBytes);
					}
					cart = new ObjectWithFile<CartEntity>(cartEntity, images);
				}else
					cart = new ObjectWithFile<CartEntity>(cartEntity, null);
				cartDetailAndFile.add(cart);
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",cartDetailAndFile));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("warning", "Danh sách rỗng!",""));
		}
	}
	@GetMapping("cart/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getCartDetailByID(@PathVariable long id){
		CartEntity cartEntity = cartServices.getCartDetailByID(id);
		if(cartEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",cartEntity));
		else
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Không tìm thấy dữ liệu!",""));
	}
	@PostMapping("cart")
	@ResponseBody
	public  ResponseEntity<ResponseObject> addNewCart(@RequestBody Cart cart){
		CartEntity cartEntity = cartServices.getCartDetailByUserIdAndBookId(cart.getUserid(), cart.getBookid());
		if(cartEntity != null) {
			int amount = cartEntity.getAmount() + cart.getAmount();
			cartServices.updateAmount(cartEntity.getId(), amount > 10 ? 10:amount);
		}else {
			cartServices.addNewCart(cart);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",""));
		
	}
	@PutMapping("cart/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateAmount(@PathVariable long id, @RequestParam int amount){
		CartEntity cartEntity = cartServices.updateAmount(id, amount);
		if(cartEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",cartEntity));
		else
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}
	@DeleteMapping("cart/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteCartDetail(@PathVariable long id){
		CartEntity cartEntity = cartServices.deleteCartDetail(id);
		if(cartEntity != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Thao tác thực hiện thành công!",cartEntity));
		else
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseObject("failed", "Thao tác không được thực hiện!",""));
	}
	
}
