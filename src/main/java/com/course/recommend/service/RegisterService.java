package com.course.recommend.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String IS_REGISTERED = "SELECT NVL(COUNT(*),0) FROM registered WHERE courseId = ? AND userName = ?";

	private static final String REGISTER = "INSERT INTO registered(COURSEID, USERNAME) VALUES(?,?)";

	private static final String UNREGISTER = "DELETE FROM registered WHERE courseId = ? AND userName = ?";

	private static final String INCREMENT_REGISTER_COUNT = "UPDATE categoryCount SET registeredCourse = registeredCourse + 1 WHERE categoryId = ? AND userName = ?";

	private static final String DECREMENT_REGISTER_COUNT = "UPDATE categoryCount SET registeredCourse = registeredCourse - 1 WHERE categoryId = ? AND userName = ?";
	
	private static final String INCREMENT_TEACHER_COUNT = "UPDATE teacherCount SET registeredCount = registeredCount + 1 WHERE teacher = ? AND userName = ?";

	private static final String DECREMENT_TEACHER_COUNT = "UPDATE teacherCount SET registeredCount = registeredCount - 1 WHERE teacher = ? AND userName = ?";

	
	public int isRegistered(int courseId, String userName) {
		return jdbcTemplate.queryForObject(IS_REGISTERED, new Object[] { courseId, userName }, Integer.class);
	}

	public void register(int courseId, String userName) {
		jdbcTemplate.update(REGISTER, courseId, userName);
	}

	public void unregister(int courseId, String userName) {
		jdbcTemplate.update(UNREGISTER, courseId, userName);
	}

	public void increment(int categoryId, String userName) {
		jdbcTemplate.update(INCREMENT_REGISTER_COUNT, categoryId, userName);
	}

	public void decrement(int categoryId, String userName) {
		jdbcTemplate.update(DECREMENT_REGISTER_COUNT, categoryId, userName);
	}
	
	public void incrementTeacher(String teacher, String userName) {
		jdbcTemplate.update(INCREMENT_TEACHER_COUNT, teacher, userName);
	}

	public void decrementTeacher(String teacher, String userName) {
		jdbcTemplate.update(DECREMENT_TEACHER_COUNT, teacher, userName);
	}
}
