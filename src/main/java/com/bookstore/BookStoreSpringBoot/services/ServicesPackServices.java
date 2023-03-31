package com.bookstore.BookStoreSpringBoot.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.BookStoreSpringBoot.convert.ServicePackAndServicePackEntity;
import com.bookstore.BookStoreSpringBoot.entity.ServicePackEntity;
import com.bookstore.BookStoreSpringBoot.object.ServicePack;
import com.bookstore.BookStoreSpringBoot.repositories.ServicePackRepository;


@Service
public class ServicesPackServices {
	
	
	@Autowired
	ServicePackRepository serviceRepository;
	@Autowired
	ServicePackAndServicePackEntity servicePackAndServicePackEntity;

	public List<ServicePackEntity> getAllServices(){
		return serviceRepository.findAll(); 
	}

	public ServicePackEntity getServicesByID(long id){
		return serviceRepository.findById(id).orElse(null);
	}
	@Transactional
	public ServicePackEntity addNewService(ServicePack service){
		ServicePackEntity serviceEntity = servicePackAndServicePackEntity.convertToServicePackEntity(service);
		serviceEntity.setStatus(0);
		return serviceRepository.save(serviceEntity);
	}
	@Transactional
	public ServicePackEntity updateService(long id, ServicePack service){
		ServicePackEntity serviceEntity =  servicePackAndServicePackEntity.convertToServicePackEntity(service);
		serviceEntity.setId(id);
		return serviceRepository.save(serviceEntity);
	}
	@Transactional
	public ServicePackEntity deleteService(long id){
		ServicePackEntity serviceEntity = getServicesByID(id);
		serviceEntity.setStatus(1);
		return serviceRepository.save(serviceEntity);
	}
}
