package com.bookstore.BookStoreSpringBoot.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bookstore.BookStoreSpringBoot.dto.response.TagResponse;
import com.bookstore.BookStoreSpringBoot.entity.TagEntity;

@Component
public class MapperUtils {
	public static String getOneBookImage(String image) {
	    if (image != null && !image.isEmpty()) {
	        // Tách chuỗi thành mảng các tên hình ảnh
	        String[] imageNames = image.split(",");
	        // Kiểm tra xem mảng có phần tử không
	        if (imageNames.length > 0) {
	            // Trả về tên hình ảnh đầu tiên
	            return imageNames[0];
	        }
	    }
	    // Nếu không có hình ảnh, trả về giá trị mặc định hoặc xử lý khác (nếu cần)
	    return "default-image.jpg"; // Ví dụ: trả về tên hình ảnh mặc định
	}
	public static List<String> getListBookImage(String image) {
	    List<String> imageNamesList = new ArrayList<>();

	    if (image != null && !image.isEmpty()) {
	        // Tách chuỗi thành mảng các tên hình ảnh
	        String[] imageNames = image.split(",");

	        // Kiểm tra xem mảng có phần tử không
	        if (imageNames.length > 0) {
	            // Thêm các tên hình ảnh vào danh sách
	            imageNamesList.addAll(Arrays.asList(imageNames));
	        }
	    }

	    // Nếu không có hình ảnh, trả về giá trị mặc định hoặc xử lý khác (nếu cần)
	    if (imageNamesList.isEmpty()) {
	        // Ví dụ: thêm tên hình ảnh mặc định vào danh sách
	        imageNamesList.add("default-image.jpg");
	    }

	    return imageNamesList;
	}
	
	public static List<TagResponse> getListTagsBook(List<TagEntity> tags) {
		List<TagResponse> tagResponses = new ArrayList<>();
		TagResponse tagResponse;
		for(TagEntity tag:tags) {
			tagResponse =  new TagResponse();
			tagResponse.setId(tag.getId());
			tagResponse.setName(tag.getName());
			tagResponses.add(tagResponse);
		}
	    return tagResponses;
	}
	
}
