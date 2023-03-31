package com.bookstore.BookStoreSpringBoot.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.convert.BookAndBookEntity;
import com.bookstore.BookStoreSpringBoot.entity.BookEntity;
import com.bookstore.BookStoreSpringBoot.repositories.BookRepository;
import com.bookstore.BookStoreSpringBoot.object.Book;
@Service
public class BookServices {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	BookAndBookEntity bookAndBookEntity;
	public List<BookEntity> getAllBook(){
		return bookRepository.findAll();
	}
	public List<BookEntity> getBooksByStoreId(long storeId){
		return bookRepository.findByStoreEntityId(storeId);
	}
	
	
	public List<BookEntity> getAllBookByStoreIDAndStatus(long storeId, int status){
		return bookRepository.findByStoreEntityIdAndStatus(storeId, status);
	}
	@Transactional
	public BookEntity getBookByID(long id){
		Optional<BookEntity> oBookEntity = bookRepository.findById(id);
		return oBookEntity.orElse(null);
	}
	@Transactional
	public BookEntity addNewBook(Book book){
		BookEntity bookEntity =  bookAndBookEntity.convertToBookEntity(book);
		bookEntity.setStatus(0);
		return bookRepository.save(bookEntity);
	}
	@Transactional
	public BookEntity updateBook(long id, Book book){
		BookEntity bookEntity = bookAndBookEntity.convertToBookEntity(book);
		bookEntity.setId(id);
		bookEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		return bookRepository.save(bookEntity);
	}
	@Transactional
	public BookEntity setBookStatus(long id, int status){
		BookEntity bookEntity = getBookByID(id);
		bookEntity.setStatus(status);
		return bookRepository.save(bookEntity);
	}
	@Transactional
	public List<BookEntity> getListRelatedBooks(long categoryId){
		return bookRepository.findByCategoryEntityIdAndStatus(categoryId, 0);
	}
}
