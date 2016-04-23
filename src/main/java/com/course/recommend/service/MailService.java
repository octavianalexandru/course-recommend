package com.course.recommend.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.course.recommend.domain.Email;

@Service
public class MailService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public static final String INSERT_MAIL = "INSERT INTO email(TEACHER, STUDENT, SUBJECT, MESSAGE, DATESENT) VALUES(?,?,?,?,?)";

	public static final String GET_FIRST_3_EMAILS_FOR_STUDENT = "SELECT teacher,student,subject,message,datesent,firstName,lastName FROM ("
			+ "SELECT teacher,student,subject,message,datesent,firstName,lastName FROM email e, users u WHERE student = ? AND e.teacher = u.username ORDER BY datesent DESC) WHERE ROWNUM < 4";
	
	public static final String GET_ALL_EMAILS_FOR_STUDENT = "SELECT teacher,student,subject,message,datesent,firstName,lastName FROM email e, users u WHERE student = ? AND e.teacher = u.username ORDER BY datesent DESC"; 
	
	public void insertEmail(Email email) {
		jdbcTemplate.update(INSERT_MAIL, email.getTeacher(), email.getStudent(), email.getSubject(), email.getMessage(),
				new java.sql.Date(Calendar.getInstance().getTime().getTime()));
	}

	public List<Email> getLatestEmails(String username) {

		List<Email> emails = jdbcTemplate.query(GET_FIRST_3_EMAILS_FOR_STUDENT, new Object[] { username },
				new RowMapper<Email>() {
					public Email mapRow(ResultSet rs, int i) throws SQLException {
						Email email = new Email(rs.getString("teacher"), rs.getString("firstName"),
								rs.getString("lastName"), rs.getString("student"), rs.getString("subject"),
								rs.getString("message"), rs.getDate("datesent"));

						return email;
					}
				});

		return emails;
	}
	
	public List<Email> getAllEmails(String username) {

		List<Email> emails = jdbcTemplate.query(GET_ALL_EMAILS_FOR_STUDENT, new Object[] { username },
				new RowMapper<Email>() {
					public Email mapRow(ResultSet rs, int i) throws SQLException {
						Email email = new Email(rs.getString("teacher"), rs.getString("firstName"),
								rs.getString("lastName"), rs.getString("student"), rs.getString("subject"),
								rs.getString("message"), rs.getDate("datesent"));

						return email;
					}
				});

		return emails;
	}


}
