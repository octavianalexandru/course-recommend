package com.course.recommend.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.course.recommend.domain.FullCourse;

@Service
public class CourseService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String GET_ID = "SELECT NVL(MAX(id),0) + 1 FROM course";

	private static final String GET_COURSES = "SELECT c.id, c.title, c.description, c.numberOfWeeks, c.cover, c.userName, c.startDate, c.endDate, ct.id courseTypeId, ct.description courseTypeDescription FROM course c, coursetype ct WHERE c.coursetypeid = ct.id";

	private static final String GET_FULL_COURSES_BY_USER = "SELECT c.id, c.title, c.description, c.numberOfWeeks, c.cover, c.userName, c.startDate, c.endDate, ct.id courseTypeId, ct.description courseTypeDescription FROM course c, coursetype ct WHERE c.userName = ? and c.coursetypeid = ct.id";

	private static final String GET_FULL_COURSE_BY_ID = "SELECT c.id, c.title, c.description, c.numberOfWeeks, c.cover, c.userName, c.startDate, c.endDate, ct.id courseTypeId, ct.description courseTypeDescription FROM course c, coursetype ct WHERE c.id = ? and c.coursetypeid = ct.id";

	private static final String GET_FULL_COURSE_BY_SEARCH_TEXT = "SELECT c.id, c.title, c.description, c.numberOfWeeks, c.cover, c.userName, c.startDate, c.endDate, ct.id courseTypeId, ct.description courseTypeDescription FROM course c, coursetype ct WHERE upper(c.title) like '%'||upper(?)||'%' and c.coursetypeid = ct.id";

	private static final String INSERT_COURSE = "INSERT INTO course(ID, TITLE, DESCRIPTION, NUMBEROFWEEKS, COVER, USERNAME, STARTDATE, ENDDATE, COURSETYPEID) VALUES(?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE_COURSE = "UPDATE course SET title = ?, description = ?, numberOfWeeks = ?, startDate = ?, endDate = ?, courseTypeId = ? WHERE id = ?";

	private static final String UPDATE_COVER = "UPDATE course SET cover = ? WHERE id = ?";

	public List<FullCourse> getAllCourses() {

		List<FullCourse> allCourses = jdbcTemplate.query(GET_COURSES, new RowMapper<FullCourse>() {
			public FullCourse mapRow(ResultSet rs, int i) throws SQLException {
				FullCourse course = new FullCourse(rs.getInt("id"), rs.getInt("numberOfWeeks"), rs.getString("title"),
						rs.getString("description"), rs.getBytes("cover"), rs.getString("userName"),
						rs.getDate("startDate"), rs.getDate("endDate"), rs.getInt("courseTypeId"),
						rs.getString("courseTypeDescription"));
				return course;
			}
		});

		return allCourses;
	}

	private int getId() {
		return jdbcTemplate.queryForObject(GET_ID, Integer.class);
	}

	public void insertCourse(FullCourse course) {
		jdbcTemplate.update(INSERT_COURSE, getId(), course.getTitle(), course.getDescription(),
				course.getNumberOfWeeks(), course.getCover(), course.getUserName(), course.getStartDate(),
				course.getEndDate(), course.getCourseTypeId());
	}

	public List<FullCourse> getCoursesByUserName(String username) {

		List<FullCourse> allCourses = jdbcTemplate.query(GET_FULL_COURSES_BY_USER, new Object[] { username },
				new RowMapper<FullCourse>() {
					public FullCourse mapRow(ResultSet rs, int i) throws SQLException {
						FullCourse course = new FullCourse(rs.getInt("id"), rs.getInt("numberOfWeeks"),
								rs.getString("title"), rs.getString("description"), rs.getBytes("cover"),
								rs.getString("userName"), rs.getDate("startDate"), rs.getDate("endDate"),
								rs.getInt("courseTypeId"), rs.getString("courseTypeDescription"));

						return course;
					}
				});

		return allCourses;
	}

	public FullCourse getFullCourseById(int courseId) {
		FullCourse fullCourse = (FullCourse) jdbcTemplate.queryForObject(GET_FULL_COURSE_BY_ID,
				new Object[] { courseId }, new RowMapper<FullCourse>() {
					public FullCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
						FullCourse fullCourse = new FullCourse(rs.getInt("id"), rs.getInt("numberOfWeeks"),
								rs.getString("title"), rs.getString("description"), rs.getBytes("cover"),
								rs.getString("userName"), rs.getDate("startDate"), rs.getDate("endDate"),
								rs.getInt("courseTypeId"), rs.getString("courseTypeDescription"));
						return fullCourse;
					}
				});

		return fullCourse;
	}

	private void updateCover(byte[] cover, int courseId) {
		jdbcTemplate.update(UPDATE_COVER, cover, courseId);
	}

	public void updateCourse(FullCourse course) {
		if (course.getCover() != null)
			updateCover(course.getCover(), course.getId());

		jdbcTemplate.update(UPDATE_COURSE, course.getTitle(), course.getDescription(), course.getNumberOfWeeks(),
				course.getStartDate(), course.getEndDate(), course.getCourseTypeId(), course.getId());
	}

	public List<FullCourse> getCoursesBySearchText(String searchText) {

		List<FullCourse> allCourses = jdbcTemplate.query(GET_FULL_COURSE_BY_SEARCH_TEXT, new Object[] { searchText },
				new RowMapper<FullCourse>() {
					public FullCourse mapRow(ResultSet rs, int i) throws SQLException {
						FullCourse course = new FullCourse(rs.getInt("id"), rs.getInt("numberOfWeeks"),
								rs.getString("title"), rs.getString("description"), rs.getBytes("cover"),
								rs.getString("userName"), rs.getDate("startDate"), rs.getDate("endDate"),
								rs.getInt("courseTypeId"), rs.getString("courseTypeDescription"));

						return course;
					}
				});

		return allCourses;
	}

}
