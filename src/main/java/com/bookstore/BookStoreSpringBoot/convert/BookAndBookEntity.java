package com.bookstore.BookStoreSpringBoot.convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.object.Book;
import com.bookstore.BookStoreSpringBoot.services.CategoryServices;
import com.bookstore.BookStoreSpringBoot.services.StoreServices;

@Component
public class BookAndBookEntity {
	@Autowired
	private CategoryServices categoryServices;
	@Autowired
	private StoreServices storeServices;
	public BookEntity convertToBookEntity(Book book) {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(book.getId());
		bookEntity.setName(book.getName());
		bookEntity.setImage(book.getImage());
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setCategoryEntity(categoryServices.getCategoriesByID(book.getCategoryId()));
		bookEntity.setPublishing(book.getPublishing());
		bookEntity.setPublishingYear(book.getPublishingYear());
		bookEntity.setStoreEntity(storeServices.getStoreByID(book.getStoreId()));
		bookEntity.setPageNumber(book.getPageNumber());
		bookEntity.setLength(book.getLength());
		bookEntity.setWidth(book.getWidth());
		bookEntity.setHeight(book.getHeight());
		bookEntity.setQuantity(book.getQuantity());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setQuantitySold(book.getQuantitySold());
		bookEntity.setCreateDate(book.getCreateDate());
		bookEntity.setUpdateDate(book.getUpdateDate());
		bookEntity.setStatus(book.getStatus());
		return bookEntity;
	}
}
