package com.bookstore.BookStoreSpringBoot.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.BookStoreSpringBoot.dto.response.AdminReport;
import com.bookstore.BookStoreSpringBoot.object.ResponseObject;
import com.bookstore.BookStoreSpringBoot.services.ReportServices;

@CrossOrigin
@RestController
@RequestMapping("BookStore/api/report")
public class RepostController {
	@Autowired
	ReportServices reportServices;
	@GetMapping("/")
	public ResponseEntity<ResponseObject> getRepost(@RequestParam Date startDate, @RequestParam Date endDate){
		try {
			AdminReport adminReport = reportServices.createReport(startDate, endDate);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", adminReport));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(500, "Thao tác thực hiện thành công!", ""));
		}
	}
}
