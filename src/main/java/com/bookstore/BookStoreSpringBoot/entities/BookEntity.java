package com.bookstore.BookStoreSpringBoot.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class BookEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private String image;
	@Column
	private String author;
	@ManyToOne
	@JoinColumn(name="categoryId", referencedColumnName = "id")
	private CategoryEntity categoryEntity;
	@Column
	private String publishing;
	@ManyToOne
	@JoinColumn(name="promotionId", referencedColumnName = "id")
	private PromotionEntity promotionEntity;
	@Column
	private int publishingYear;
	@ManyToOne
	@JoinColumn(name="storeId", referencedColumnName = "id")
	private StoreEntity storeEntity;
	@Column
	private int pageNumber;
	@Column
	private int length;
	@Column
	private int width;
	@Column
	private int height;
	@Column
	private float weight;
	@Column
	private int quantity;
	@Column
	private float price;
	@Column
	private int quantitySold;
	@Column
	private Date createDate;
	@Column
	private Date updateDate;
	@Column
	private int status;
	@Column
	private String description;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "book_tag",
        joinColumns = { @JoinColumn(name = "bookid") },
        inverseJoinColumns = { @JoinColumn(name = "tagid") }
    )
    private Set<TagEntity> tags = new HashSet<TagEntity>();
	public long getId() {
		return id;
	}
	@OneToMany(mappedBy = "bookEntity")
	public void setId(long id) {
		this.id = id;
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
