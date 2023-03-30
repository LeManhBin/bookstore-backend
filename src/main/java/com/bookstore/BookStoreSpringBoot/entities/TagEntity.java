package com.bookstore.BookStoreSpringBoot.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tag")
public class TagEntity {
   
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private int status;
	 @ManyToMany(mappedBy = "tags")

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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
	public TagEntity() {
		super();
	}
 
	public TagEntity(long id, String name, int status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	} 
}

