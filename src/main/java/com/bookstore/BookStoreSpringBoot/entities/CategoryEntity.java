package com.bookstore.BookStoreSpringBoot.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private String thumbnail;
	@Column
	private int status;
	@OneToMany(mappedBy = "categoryEntity")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", name=" + name + ", thumbnail=" + thumbnail + ", status=" + status + "]";
	}
	public CategoryEntity() {
		super();
	}
	public CategoryEntity(long id, String name, String thumbnail, int status) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
		this.status = status;
	}
}
