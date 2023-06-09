package com.bookstore.BookStoreSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ComponentScan("com.bookstore.BookStoreSpringBoot")
@EntityScan(basePackages = "com.bookstore.BookStoreSpringBoot.entity")
@EnableJpaRepositories("com.bookstore.BookStoreSpringBoot.repositories")
public class BookStoreSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookStoreSpringBootApplication.class, args);
	}

}
