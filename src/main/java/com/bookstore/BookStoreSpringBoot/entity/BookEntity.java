package com.bookstore.BookStoreSpringBoot.entity;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="book")
public class BookEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String image;
	private String author;
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "id")
	private CategoryEntity categoryEntity;
	private String publishing;
	@ManyToOne
	@JoinColumn(name="promotion_id", referencedColumnName = "id")
	private PromotionEntity promotionEntity;
	@Column(name="publishing_year")
	private Integer publishingYear;
	@ManyToOne
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;
	@Column(name="page_number")
	private Integer pageNumber;
	private Integer length;
	private Integer width;
	private Integer height;
	private Integer weight;
	private Integer quantity;
	private Integer price;
	@Column(name="quantity_sold")
	private Integer quantitySold;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="update_date")
	private Date updateDate;
	private int status;
	private String description;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "book_tag",
        joinColumns = { @JoinColumn(name = "book_id") },
        inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private List<TagEntity> tags = new ArrayList<TagEntity>();
}
