package com.bookstore.BookStoreSpringBoot.convert;

import org.springframework.stereotype.Component;


import com.bookstore.BookStoreSpringBoot.entity.ServicePackEntity;
import com.bookstore.BookStoreSpringBoot.object.ServicePack;

@Component
public class ServicePackAndServicePackEntity {
	
	public ServicePackEntity convertToServicePackEntity(ServicePack service) {
		ServicePackEntity serviceEntity = new ServicePackEntity();
		serviceEntity.setName(service.getName());
		serviceEntity.setPrice(service.getPrice());
		serviceEntity.setQuantityProduct(service.getQuantityProduct());
		serviceEntity.setExpirationDate(service.getExpirationDate());
		serviceEntity.setThumbnail(service.getThumbnail());
		serviceEntity.setDescription(service.getDescription());
		serviceEntity.setStatus(service.getStatus());
		return serviceEntity;
	}
}
