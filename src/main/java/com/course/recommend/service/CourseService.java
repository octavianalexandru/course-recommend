package com.course.recommend.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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
	private static final String GET_ID_USER_COURSE = "SELECT NVL(MAX(id),0) + 1 FROM USER_COURSE";

	private static final String GET_COURSES = "SELECT c.id, c.title, c.description, c.numberOfWeeks, c.cover, c.userName, c.startDate, c.endDate, ct.id courseTypeId, ct.description courseTypeDescription, c.enteredDate FROM course c, coursetype ct WHERE c.coursetypeid = ct.id";

	private static final String GET_FULL_COURSES_BY_USER = "SELECT c.id, c.title, c.description, c.numberOfWeeks, c.cover, c.userName, c.startDate, c.endDate, ct.id courseTypeId, ct.description courseTypeDescription, c.enteredDate FROM course c, coursetype ct WHERE c.userName = ? and c.coursetypeid = ct.id";

	private static final String GET_FULL_COURSE_BY_ID = "SELECT c.id, c.title, c.description, c.numberOfWeeks, c.cover, c.userName, c.startDate, c.endDate, ct.id courseTypeId, ct.description courseTypeDescription, c.enteredDate FROM course c, coursetype ct WHERE c.id = ? and c.coursetypeid = ct.id";

	private static final String GET_FULL_COURSE_BY_SEARCH_TEXT = "SELECT c.id, c.title, c.description, c.numberOfWeeks, c.cover, c.userName, c.startDate, c.endDate, ct.id courseTypeId, ct.description courseTypeDescription, c.enteredDate FROM course c, coursetype ct WHERE upper(c.title) like '%'||upper(?)||'%' and c.coursetypeid = ct.id";

	private static final String INSERT_COURSE = "INSERT INTO course(ID, TITLE, DESCRIPTION, NUMBEROFWEEKS, COVER, USERNAME, STARTDATE, ENDDATE, COURSETYPEID, ENTEREDDATE) VALUES(?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE_COURSE = "UPDATE course SET title = ?, description = ?, numberOfWeeks = ?, startDate = ?, endDate = ?, courseTypeId = ? WHERE id = ?";

	private static final String UPDATE_COVER = "UPDATE course SET cover = ? WHERE id = ?";

	private static final String GET_RATING_USER_COURSE = "SELECT uc.rating from USER_COURSE uc where  uc.username = ? and uc.courseid=?";
	
	private static final String INSERT_RATING = "INSERT INTO USER_COURSE(ID,USERNAME,COURSEID,RATING) VALUES(?,?,?,?)";
	
	private static final String UPDATE_RATING = "UPDATE USER_COURSE SET rating= ? where username = ? and courseid = ?";
	
	private static final String GET_COURSE_AVERAGE_RATING = "SELECT AVG(rating) FROM USER_COURSE WHERE COURSEID = ? GROUP BY COURSEID";
	
	private static final String GET_COURSE_RATING = "SELECT rating FROM USER_COURSE WHERE USERNAME = ? and COURSEID = ?";
	
	private static final String GET_TOP_RATED_COURSE_IDS = "SELECT uc.courseid FROM course c RIGHT JOIN (SELECT courseid,AVG(rating) avgrating FROM user_course GROUP BY courseid) uc ON uc.courseid = c.id WHERE rownum  <=30 ORDER BY uc.avgrating DESC";
	
	private static final String GET_NEW_ADDED_COURSES = "SELECT c.id,c.title,c.description,c.numberOfWeeks,c.cover,c.userName,c.startDate,c.endDate,ct.id courseTypeId,ct.description courseTypeDescription,c.enteredDate FROM course c, coursetype ct WHERE c.coursetypeid = ct.id and c.ENTEREDDATE > sysdate-15 ORDER BY c.ENTEREDDATE";
	
	public List<FullCourse> getAllCourses() {

		List<FullCourse> allCourses = jdbcTemplate.query(GET_COURSES, new RowMapper<FullCourse>() {
			public FullCourse mapRow(ResultSet rs, int i) throws SQLException {
				FullCourse course = new FullCourse(rs.getInt("id"), rs.getInt("numberOfWeeks"), rs.getString("title"),
						rs.getString("description"), rs.getBytes("cover"), rs.getString("userName"),
						rs.getDate("startDate"), rs.getDate("endDate"), rs.getInt("courseTypeId"),
						rs.getString("courseTypeDescription"),rs.getDate("enteredDate"));
				return course;
			}
		});

		return allCourses;
	}

	private int getId() {
		return jdbcTemplate.queryForObject(GET_ID, Integer.class);
	}
	
	private int getIdUserCourse(){
		return jdbcTemplate.queryForObject(GET_ID_USER_COURSE, Integer.class);
	}
	
	private int getRatingUserCourse(String username, int courseId){
		
		return jdbcTemplate.queryForObject(GET_RATING_USER_COURSE, Integer.class, new Object[]{username,courseId});
	}

	public void insertCourse(FullCourse course) {
		jdbcTemplate.update(INSERT_COURSE, getId(), course.getTitle(), course.getDescription(),
				course.getNumberOfWeeks(), course.getCover(), course.getUserName(), course.getStartDate(),
				course.getEndDate(), course.getCourseTypeId(),course.getEnteredDate());
	}

	public List<FullCourse> getCoursesByUserName(String username) {

		List<FullCourse> allCourses = jdbcTemplate.query(GET_FULL_COURSES_BY_USER, new Object[] { username },
				new RowMapper<FullCourse>() {
					public FullCourse mapRow(ResultSet rs, int i) throws SQLException {
						FullCourse course = new FullCourse(rs.getInt("id"), rs.getInt("numberOfWeeks"),
								rs.getString("title"), rs.getString("description"), rs.getBytes("cover"),
								rs.getString("userName"), rs.getDate("startDate"), rs.getDate("endDate"),
								rs.getInt("courseTypeId"), rs.getString("courseTypeDescription"),rs.getDate("enteredDate"));

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
								rs.getInt("courseTypeId"), rs.getString("courseTypeDescription"),rs.getDate("enteredDate"));
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
								rs.getInt("courseTypeId"), rs.getString("courseTypeDescription"),rs.getDate("enteredDate"));

						return course;
					}
				});

		return allCourses;
	}
	
	public void insertUpdateRating(String username,int courseId,int rating) {
		
		
		
			try{
			jdbcTemplate.update(INSERT_RATING, getIdUserCourse(),username,courseId,rating);
			}
			catch(DuplicateKeyException e){
				jdbcTemplate.update(UPDATE_RATING,rating,username,courseId);
			}
		
			
			
	}
	
	public double getCourseAverageRating(int courseId){
		double averageRating;
		
		try{
		averageRating = jdbcTemplate.queryForObject(GET_COURSE_AVERAGE_RATING, new Object[]{courseId}, Double.class);
		}
		catch(EmptyResultDataAccessException e){
			averageRating = 0;
		}
		
		return averageRating;
		
	}
	
	public int getCourseRating(String username, int courseId){
		int rating;
		
		try{
			rating = jdbcTemplate.queryForObject(GET_COURSE_RATING,new Object[]{username,courseId}, Integer.class);
		}
		catch(EmptyResultDataAccessException e){
			rating = 0;
		}
		return rating;
	}
	
	public List<Integer> getTopRatedCourses(){
		List<Integer> topRatedCourses = jdbcTemplate.queryForList(GET_TOP_RATED_COURSE_IDS,Integer.class);
			
		
		
		return topRatedCourses;
	}
	
	public Map<Date, ArrayList<FullCourse>> getNewAddedCourses(){
		List<FullCourse> newAddedCourses = jdbcTemplate.query(GET_NEW_ADDED_COURSES, new RowMapper<FullCourse>() {
			public FullCourse mapRow(ResultSet rs, int i) throws SQLException {
				FullCourse course = new FullCourse(rs.getInt("id"), rs.getInt("numberOfWeeks"), rs.getString("title"),
						rs.getString("description"), rs.getBytes("cover"), rs.getString("userName"),
						rs.getDate("startDate"), rs.getDate("endDate"), rs.getInt("courseTypeId"),
						rs.getString("courseTypeDescription"),rs.getDate("enteredDate"));
				return course;
			}
		});
		
		Map<Date, ArrayList<FullCourse>> newAddedCoursesWithDates= new HashMap<Date,ArrayList<FullCourse>>();
		NavigableSet<Date> courseDatesUnique = new TreeSet<Date>();
		
		for (FullCourse course:newAddedCourses){
			courseDatesUnique.add(course.getEnteredDate());
			
		}
		
		courseDatesUnique=courseDatesUnique.descendingSet();
		
		for(Date date:courseDatesUnique){
			ArrayList<FullCourse> thisDayCourses = new ArrayList<FullCourse>();
			for(FullCourse course:newAddedCourses){
				
				if(course.getEnteredDate().equals(date)){
					thisDayCourses.add(course);
					System.out.println(course.getId()+" "+course.getEnteredDate());
				}
			}
			newAddedCoursesWithDates.put(date,thisDayCourses);
		}
		
		return newAddedCoursesWithDates;
	}
}
