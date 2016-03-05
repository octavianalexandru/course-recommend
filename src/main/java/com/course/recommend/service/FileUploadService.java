package com.course.recommend.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FileUploadService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String HAS_PHOTO = "SELECT COUNT(USERNAME) FROM userPhoto WHERE userName = ?";
	private static final String GET_PHOTO = "SELECT photo FROM userPhoto WHERE userName = ?";
	private static final String INSERT_PHOTO = "INSERT INTO userPhoto(USERNAME,PHOTO) " + "VALUES(?,?)";
	private static final String UPDATE_PHOTO = "UPDATE userPhoto SET photo = ? WHERE userName = ?";

	public void insertPhoto(byte[] photo, String username) {

		boolean hasPhoto = jdbcTemplate.queryForObject(HAS_PHOTO, new Object[] { username }, Integer.class) > 0 ? true
				: false;

		if (hasPhoto)
			jdbcTemplate.update(UPDATE_PHOTO, photo, username);
		else
			jdbcTemplate.update(INSERT_PHOTO, username, photo);
	}
	
	public byte[] getPhoto(String username){
		return jdbcTemplate.queryForObject(GET_PHOTO, new Object[] { username }, byte[].class);
	}
}
