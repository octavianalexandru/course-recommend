package com.course.recommend.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public static final String GET_NUMBER_OF_COURSES = "SELECT NVL(COUNT(*),0) FROM course";
	
	public static final String GET_NUMBER_OF_COURSES_BY_USER = "SELECT NVL(COUNT(*),0) FROM course WHERE userName = ?";
	
	public static final String GET_NUMBER_OF_STUDENTS = "SELECT NVL(COUNT(*),0) FROM users WHERE role = 'ROLE_USER'";
	
	public static final String GET_NUMBER_OF_STUDENTS_BY_USER = "SELECT 0 FROM users WHERE role = 'ROLE_USER'";
	
	public static final String GET_NUMBER_OF_COURSES_BY_TYPE_AND_USER = "SELECT NVL(COUNT(*),0) FROM course WHERE courseTypeId = ? and userName = ?";
	
	public int getNumberOfCourses() {
		return jdbcTemplate.queryForObject(GET_NUMBER_OF_COURSES, Integer.class);
	}
	
	public int getCoursesByUser(String userName){
		return jdbcTemplate.queryForObject(GET_NUMBER_OF_COURSES_BY_USER, new Object[]{userName}, Integer.class);
	}
	
	public int getNumberOfStudents() {
		return jdbcTemplate.queryForObject(GET_NUMBER_OF_STUDENTS, Integer.class);
	}
	
	public int getStudentsByUser(String userName){
//		return jdbcTemplate.queryForObject(GET_NUMBER_OF_STUDENTS_BY_USER, new Object[]{userName}, Integer.class);
		return 0;
	}
	
	public int getNumberOfCoursesByTypeAndUser(int courseTypeId, String userName){
		return jdbcTemplate.queryForObject(GET_NUMBER_OF_COURSES_BY_TYPE_AND_USER, new Object[]{courseTypeId,userName}, Integer.class);
	}
}
