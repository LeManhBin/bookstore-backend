package com.bookstore.BookStoreSpringBoot.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="contact")
public class ContactEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private String gmail;
	@Column
	private String subject;
	@Column
	private String content;
	@Column
	private Date createDate;
	@Column
	private int status;
	public ContactEntity() {
		super();
	}
	public ContactEntity(long id, String name, String gmail, String subject, String content, Date createDate, int status) {
		super();
		this.id = id;
		this.name = name;
		this.gmail = gmail;
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
		this.status = status;
	}
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
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", gmail=" + gmail + ", subject=" + subject + ", content="
				+ content + ", createDate=" + createDate + ", status=" + status + "]";
	}
	
}
