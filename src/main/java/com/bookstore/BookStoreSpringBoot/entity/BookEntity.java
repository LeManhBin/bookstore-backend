package com.bookstore.BookStoreSpringBoot.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="book")
public class BookEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
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
	private int publishingYear;
	@ManyToOne
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;
	@Column(name="page_number")
	private int pageNumber;
	private int length;
	private int width;
	private int height;
	private float weight;
	private int quantity;
	private float price;
	@Column(name="quantity_sold")
	private int quantitySold;
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
    private Set<TagEntity> tags = new HashSet<TagEntity>();
    @OneToMany(mappedBy = "bookEntity")
    
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}
	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}
	public String getPublishing() {
		return publishing;
	}
	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}
	public PromotionEntity getPromotionEntity() {
		return promotionEntity;
	}
	public void setPromotionEntity(PromotionEntity promotionEntity) {
		this.promotionEntity = promotionEntity;
	}
	public int getPublishingYear() {
		return publishingYear;
	}
	public void setPublishingYear(int publishingYear) {
		this.publishingYear = publishingYear;
	}
	public StoreEntity getStoreEntity() {
		return storeEntity;
	}
	public void setStoreEntity(StoreEntity storeEntity) {
		this.storeEntity = storeEntity;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<TagEntity> getTags() {
		return tags;
	}
	public void setTags(Set<TagEntity> tags) {
		this.tags = tags;
	}
	public BookEntity() {
		super();
	}
	
}
