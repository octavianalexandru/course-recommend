package com.course.recommend.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.course.recommend.domain.CourseType;

@Service
public class CourseTypeService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String GET_COURSE_TYPES = "SELECT id,description FROM courseType ORDER BY description";

	private static final String GET_TYPE_BY_ID = "SELECT id,description FROM courseType WHERE id = ?";

	public List<CourseType> getAllTypes() {

		List<CourseType> allTypes = jdbcTemplate.query(GET_COURSE_TYPES, new RowMapper<CourseType>() {
			public CourseType mapRow(ResultSet rs, int i) throws SQLException {
				CourseType type = new CourseType(rs.getInt("id"), rs.getString("description"));
				return type;
			}
		});

		return allTypes;
	}

	public CourseType getTypeById(int courseTypeId) {

		CourseType type = (CourseType) jdbcTemplate.queryForObject(GET_TYPE_BY_ID, new Object[] { courseTypeId },
				new RowMapper<CourseType>() {
					public CourseType mapRow(ResultSet rs, int rowNum) throws SQLException {
						CourseType type = new CourseType(rs.getInt("id"), rs.getString("description"));
						return type;
					}
				});

		return type;

	}
}
