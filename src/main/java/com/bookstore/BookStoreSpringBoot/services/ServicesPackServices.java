package com.bookstore.BookStoreSpringBoot.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.BookStoreSpringBoot.dto.response.ServicePackResponseDTO;
import com.bookstore.BookStoreSpringBoot.entity.ServicePackEntity;
import com.bookstore.BookStoreSpringBoot.mapper.ServicePackMapper;
import com.bookstore.BookStoreSpringBoot.repositories.ServicePackRepository;


@Service
public class ServicesPackServices {
	
	@Autowired
	ServicePackRepository serviceRepository;
	@Autowired
	StorageServices storageServices;
	@Autowired
	ServicePackMapper servicePackMapper;
	public List<ServicePackResponseDTO> getAllServices() throws IOException{
		List<ServicePackEntity> servicePackEntities = serviceRepository.findAll(); 
		if(servicePackEntities.size() > 0) {
			List<ServicePackResponseDTO> serviceResponseDTOs = new ArrayList<ServicePackResponseDTO>();
			ServicePackResponseDTO serviceResponseDTO;
			for(ServicePackEntity servicePackEntity:servicePackEntities) {
				serviceResponseDTO = servicePackMapper.toServicePackResponseDTO(servicePackEntity);
				if(servicePackEntity.getThumbnail() != null) {
					byte[] thumbnailByte = storageServices.convertFileToByte(servicePackEntity.getThumbnail());
					serviceResponseDTO.setThumbnailByte(thumbnailByte);
				}
				serviceResponseDTOs.add(serviceResponseDTO);
			}
			return serviceResponseDTOs;
		}else
			return null;
	}

	public ServicePackResponseDTO getServicesByID(long id) throws IOException{
		ServicePackEntity servicePackEntity =  serviceRepository.findById(id).orElse(null);
		if(servicePackEntity != null) {
			ServicePackResponseDTO 	serviceResponseDTO = servicePackMapper.toServicePackResponseDTO(servicePackEntity);
			if(servicePackEntity.getThumbnail() != null) {
				byte[] thumbnailByte = storageServices.convertFileToByte(servicePackEntity.getThumbnail());
				serviceResponseDTO.setThumbnailByte(thumbnailByte);
			}
			return serviceResponseDTO;
		}else 
			return null;
	}

	public ServicePackEntity addNewService(ServicePackEntity service, MultipartFile file) throws IOException{
		if(file != null) {
			String path = storageServices.saveFile(file);
			service.setThumbnail(path);
		}
		service.setStatus(0);
		return serviceRepository.save(service);
	}

	public ServicePackEntity updateService(long id, ServicePackEntity service, MultipartFile file) throws IOException{
		if(file != null) {
			String path = storageServices.saveFile(file);
			service.setThumbnail(path);
		}
		service.setId(id);
		return serviceRepository.save(service);
	}

	public ServicePackEntity deleteService(long id){
		ServicePackEntity serviceEntity = serviceRepository.findById(id).get();
		serviceEntity.setStatus(1);
		return serviceRepository.save(serviceEntity);
	}
}
