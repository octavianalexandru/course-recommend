package com.course.recommend.domain;

import java.sql.Date;

public class FullCourse {
	
	private int id;
	private int numberOfWeeks;
	private int courseTypeId;
	private String courseTypeDescription;
	private String title;
	private String description;
	private String userName;
	private Date startDate;
	private Date endDate;
	private byte[] cover;
	private Date enteredDate;
	
	

	public FullCourse(int id, int numberOfWeeks, String title, String description, byte[] cover, String userName, Date startDate, Date endDate, int courseTypeId, String courseTypeDescription, Date enteredDate) {
		super();
		this.id = id;
		this.numberOfWeeks = numberOfWeeks;
		this.title = title;
		this.description = description;
		this.cover = cover;
		this.userName = userName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.courseTypeDescription = courseTypeDescription;
		this.courseTypeId = courseTypeId;
		this.enteredDate = enteredDate;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(int courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public String getCourseTypeDescription() {
		return courseTypeDescription;
	}

	public void setCourseTypeDescription(String courseTypeDescription) {
		this.courseTypeDescription = courseTypeDescription;
	}
	

	
}
