package com.bookstore.BookStoreSpringBoot.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.BookStoreSpringBoot.dto.request.BookRequestDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookBasicInfoDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookExtendInforDTO;
import com.bookstore.BookStoreSpringBoot.dto.response.BookFullInforDTO;
import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.entity.TagEntity;
import com.bookstore.BookStoreSpringBoot.mapper.BookMapper;
import com.bookstore.BookStoreSpringBoot.mapper.StoreMapper;
import com.bookstore.BookStoreSpringBoot.mapper.TagMapper;
import com.bookstore.BookStoreSpringBoot.repositories.BookRepository;
import com.bookstore.BookStoreSpringBoot.repositories.OrderDetailRepository;
import com.bookstore.BookStoreSpringBoot.repositories.TagRepository;

@Service
public class BookServices {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	ImageStorageService imageStorageService;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	StoreMapper storeMapper;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	TagMapper tagMapper;

	public List<BookBasicInfoDTO> getAllBook() throws IOException {
		List<BookEntity> bookEntities = bookRepository.findAll();
		List<BookBasicInfoDTO> bookBasicInforDTOs = bookMapper.toBookBasicInforDTO(bookEntities);
		return bookBasicInforDTOs;
	}

	public List<BookBasicInfoDTO> getBooksByStoreId(long storeId) throws IOException {
		List<BookEntity> bookEntities = bookRepository.findByStoreEntityId(storeId);
		List<BookBasicInfoDTO> bookBasicInforDTOs = bookMapper.toBookBasicInforDTO(bookEntities);
		return bookBasicInforDTOs;
	}

	public List<BookBasicInfoDTO> getBookByKeyword(String key) throws IOException {
		List<BookEntity> bookEntities = bookRepository
				.findByNameContainingOrAuthorContainingOrStoreEntityNameContaining(key, key, key);
		List<BookBasicInfoDTO> bookBasicInforDTOs = bookMapper.toBookBasicInforDTO(bookEntities);
		return bookBasicInforDTOs;
	}

	public List<BookExtendInforDTO> getAllBookByStoreIDAndStatus(long storeId, int status) throws IOException {
		List<BookEntity> bookEntities = bookRepository.findByStoreEntityIdAndStatus(storeId, status);
		List<BookExtendInforDTO> bookExtendInforDTOs = bookMapper.toBookExtendInforDTO(bookEntities);
		return bookExtendInforDTOs;
	}

	public BookFullInforDTO getBookByID(long id) throws IOException {
		BookEntity bookEntity = bookRepository.findById(id).orElse(null);
		if (bookEntity != null) {
			BookFullInforDTO bookDTO = bookMapper.toBookFullInforDTO(bookEntity);
			return bookDTO;
		}
		return null;
	}

	public BookEntity addNewBook(BookRequestDTO book, MultipartFile[] files) throws IOException {
		BookEntity bookEntity = bookMapper.toBookEntity(book);
		if (files != null) {
			String filePath = imageStorageService.storeMultiFile(files);
			bookEntity.setImage(filePath);
		}
		List<TagEntity> tags = new ArrayList<>();
		for (Long tagId : book.getTagId()) {
			TagEntity tag = tagRepository.findById(tagId).get();
			tags.add(tag);
		}
		bookEntity.setPromotionEntity(null);
		bookEntity.setTags(tags);
		bookEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		bookEntity.setQuantitySold(0);
		bookEntity.setStatus(0);
		return bookRepository.save(bookEntity);
	}

	public BookEntity updateBook(long id, BookRequestDTO book, MultipartFile[] files) throws IOException {
		BookEntity bookEntity = bookMapper.toBookEntity(book);
		if (files != null) {
			String imagePaths = imageStorageService.storeMultiFile(files);
			bookEntity.setImage(imagePaths);
		}
		bookRepository.deleteBookTagsByBookId(id);
		List<TagEntity> tags = new ArrayList<>();
		for (Long tagId : book.getTagId()) {
			TagEntity tag = tagRepository.findById(tagId).get();
			tags.add(tag);
		}
		bookEntity.setTags(tags);
		bookEntity.setId(id);
		bookEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		return bookRepository.save(bookEntity);
	}

	public BookEntity setBookStatus(long id, int status) {
		BookEntity bookEntity = bookRepository.findById(id).get();
		bookEntity.setStatus(status);
		return bookRepository.save(bookEntity);
	}

	public List<BookBasicInfoDTO> getListRelatedBooks(long categoryId) throws IOException {
		List<BookEntity> bookEntities = bookRepository.findByCategoryEntityIdAndStatus(categoryId, 0);
		List<BookBasicInfoDTO> bookBasicInfoDTOs = bookMapper.toBookBasicInforDTO(bookEntities);
		return bookBasicInfoDTOs;
	}

	public List<BookBasicInfoDTO> getBookForAddNewPromotion(long storeId, Date startDate, Date endDate) {
		List<BookEntity> bookEntities1 = bookRepository.findBooksByStoreIdAndPromotionIsNull(storeId);
		List<BookEntity> bookEntities2 = bookRepository.findBooksByStoreEntityIdAndDateNotBetween(storeId, startDate,
				endDate);
		bookEntities1.addAll(bookEntities2);
		if (bookEntities1.size() > 0) {
			return bookMapper.toBookBasicInforDTO(bookEntities1);
		} else
			return null;
	}

	public List<BookBasicInfoDTO> getBookForUpdatePromotion(long storeId, long promotioneId) {
		List<BookEntity> bookEntities = bookRepository.findBookeByStoreEntityIdAndPromotionId(storeId, promotioneId);
		if (bookEntities.size() > 0) {
			for (BookEntity bookEntity : bookEntities)
				if (bookEntity.getPromotionEntity() != null) {
					bookEntity.setStatus(1);
				} else
					bookEntity.setStatus(0);
			List<BookBasicInfoDTO> bookBasicInforDTO = bookMapper.toBookBasicInforDTO(bookEntities);
			return bookBasicInforDTO;
		} else
			return null;
	}

	public List<BookBasicInfoDTO> findBestSellingBooksOfWeek() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startOfWeek = now.with(ChronoField.DAY_OF_WEEK, 1).truncatedTo(ChronoUnit.DAYS);
		LocalDateTime endOfWeek = startOfWeek.plusDays(7);
		List<BookEntity> bookEntities = orderDetailRepository.findBestSellingBooksOfWeek(startOfWeek, endOfWeek);
		List<BookBasicInfoDTO> bookBasicInfoDTOs = bookMapper.toBookBasicInforDTO(bookEntities);
		return bookBasicInfoDTOs;

	}
}
