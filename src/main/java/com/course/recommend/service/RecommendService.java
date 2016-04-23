package com.course.recommend.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.course.recommend.domain.FullCourse;

@Service
public class RecommendService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public static final String HAS_CATEGORY = "SELECT NVL(COUNT(*),0) FROM categoryCount WHERE userName = ? and categoryId = ?";

	public static final String HAS_TEACHER_COUNT = "SELECT NVL(COUNT(*),0) FROM teacherCount WHERE teacher = ? and userName = ?";

	public static final String INSERT_COUNT = "INSERT INTO categoryCount(CATEGORYID, USERNAME, CLICKS, REGISTEREDCOURSE) VALUES(?,?,1,0)";

	public static final String INCREMENT_COUNT = "UPDATE categoryCount set clicks = clicks + 1 WHERE userName = ? AND categoryId = ?";

	public static final String INCREMENT_REGISTER_COUNT = "UPDATE categoryCount set registeredcourse = registeredcourse + 1 WHERE userName = ? AND categoryId = ?";

	public static final String INSERT_TEACHER_COUNT = "INSERT INTO teacherCount(TEACHER, USERNAME, CLICKS, REGISTEREDCOUNT) VALUES(?,?,1,0)";

	public static final String INCREMENT_TEACHER_COUNT = "UPDATE teacherCount SET clicks = clicks + 1 WHERE teacher = ? AND userName = ?";

	private int hasCategory(int categoryId, String userName) {
		return jdbcTemplate.queryForObject(HAS_CATEGORY, new Object[] { userName, categoryId }, Integer.class);
	}

	private int hasTeacherCount(String teacher, String userName) {
		return jdbcTemplate.queryForObject(HAS_TEACHER_COUNT, new Object[] { teacher, userName }, Integer.class);
	}

	public void onClick(FullCourse course, String userName) {
		if (hasCategory(course.getCourseTypeId(), userName) > 0) {
			jdbcTemplate.update(INCREMENT_COUNT, userName, course.getCourseTypeId());
		} else {
			jdbcTemplate.update(INSERT_COUNT, course.getCourseTypeId(), userName);
		}
	}

	public void onClickTeacher(String teacher, String userName) {
		if (hasTeacherCount(teacher, userName) > 0) {
			jdbcTemplate.update(INCREMENT_TEACHER_COUNT, teacher, userName);
		} else {
			jdbcTemplate.update(INSERT_TEACHER_COUNT, teacher, userName);
		}
	}

	public void incrementRegister(FullCourse course, String userName) {
		jdbcTemplate.update(INCREMENT_REGISTER_COUNT, userName, course.getCourseTypeId());
	}

}
