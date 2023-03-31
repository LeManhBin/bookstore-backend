package com.bookstore.BookStoreSpringBoot.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.entity.ContactEntity;
import com.bookstore.BookStoreSpringBoot.object.Contact;
import com.bookstore.BookStoreSpringBoot.repositories.ContactRepository;

@Service
public class ContactServices {
	@Autowired
	ContactRepository contactRepository;

	public List<ContactEntity> getAllContact(int status ){
		return contactRepository.findByStatus(status);
	}

	public ContactEntity getContactByID(long id){
		return contactRepository.findById(id).orElse(null);
	}

	public ContactEntity addNewContact(Contact contact){
		ContactEntity contactEntity = new ContactEntity();
		contactEntity.setName(contact.getName());
		contactEntity.setGmail(contact.getGmail());
		contactEntity.setSubject(contact.getSubject());
		contactEntity.setContent(contact.getContent());
		contactEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		contactEntity.setStatus(0);
		return contactRepository.save(contactEntity);
	}
	@Transactional
	public ContactEntity updateContact(long id){
		ContactEntity contactEntity =  getContactByID(id);
		contactEntity.setStatus(1);
		return contactRepository.save(contactEntity);
	}
	@Transactional
	public ContactEntity deleteContact(long id){
		 contactRepository.deleteById(id);
		 return getContactByID(id);
	}
}
