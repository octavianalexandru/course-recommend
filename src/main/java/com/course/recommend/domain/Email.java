package com.course.recommend.domain;

import java.sql.Date;

public class Email {
	private String teacher;
	private String firstName;
	private String lastName;
	private String student;
	private String subject;
	private String message;
	private Date datesent;

	public Email(String teacher, String firstName, String lastName, String student, String subject, String message,
			Date datesent) {
		super();
		this.teacher = teacher;
		this.student = student;
		this.subject = subject;
		this.message = message;
		this.datesent = datesent;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDatesent() {
		return datesent;
	}

	public void setDatesent(Date datesent) {
		this.datesent = datesent;
	}

}
