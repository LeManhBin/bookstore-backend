package com.bookstore.BookStoreSpringBoot.object;

import java.util.List;

public class ObjectWithFile <T>{
	public ObjectWithFile() {
		super();
	}
	private T object;
	private List<byte[]> imageByte;
	
	public ObjectWithFile(T object, List<byte[]> imageByte) {
		super();
		this.object = object;
		this.imageByte = imageByte;
	}
	public ObjectWithFile(T object) {
		super();
		this.object = object;
	}
	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public List<byte[]> getFile() {
		return imageByte;
	}

	public void setFile(List<byte[]> imageByte) {
		this.imageByte = imageByte;
	}
}
