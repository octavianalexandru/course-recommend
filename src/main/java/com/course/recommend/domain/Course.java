package com.course.recommend.domain;

public class Course {
	
	private int id;
	private int numberOfWeeks;
	private String title;
	private String description;
	private String userName;
	private byte[] cover;
	

	public Course(int id, int numberOfWeeks, String title, String description, byte[] cover, String userName) {
		super();
		this.id = id;
		this.numberOfWeeks = numberOfWeeks;
		this.title = title;
		this.description = description;
		this.cover = cover;
		this.userName = userName;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfWeeks() {
		return numberOfWeeks;
	}

	public void setNumberOfWeeks(int numberOfWeeks) {
		this.numberOfWeeks = numberOfWeeks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] cover) {
		this.cover = cover;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
