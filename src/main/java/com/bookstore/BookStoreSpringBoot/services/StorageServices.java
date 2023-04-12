package com.bookstore.BookStoreSpringBoot.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class StorageServices {
   private 
   @Value("${spring.servlet.multipart.location}")
   String uploadDir;
   public String saveFile(MultipartFile file) throws IOException {
	   Path rootLocation =  Paths.get(uploadDir);
	   String fileName = file.getOriginalFilename();
	   InputStream inputStream = file.getInputStream();
       Path filePath = rootLocation.resolve(fileName);
       Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
       return filePath.toAbsolutePath().toString();
   }

   public String saveMultiFile(MultipartFile[] files) throws IOException {
	   Path rootLocation =  Paths.get(uploadDir);
		String filePaths = "";
		for(MultipartFile file:files) {
			 String fileName = file.getOriginalFilename();
			   InputStream inputStream = file.getInputStream();
		       Path path = rootLocation.resolve(fileName);
		       Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
		       String filePath = path.toAbsolutePath().toString();
			filePaths+= filePath + "," ;
		}
		return filePaths;
   }
   public byte[] convertFileToByte(String path) throws IOException {
		Path imagePath = Paths.get(path);
		byte[] imageBytes = Files.readAllBytes(imagePath);
		return imageBytes;
   }
   public List<byte[]> convertMultiFileToBytes(String path) throws IOException {
		List<byte[]> imageBytes = new ArrayList<byte[]>();
		String imageNames[] = path.split(",");
		for(String imageString:imageNames) {
			Path imagePath = Paths.get(imageString);
			byte[] bytes = Files.readAllBytes(imagePath);
			imageBytes.add(bytes);
		}
		return imageBytes;
   }
}
	
