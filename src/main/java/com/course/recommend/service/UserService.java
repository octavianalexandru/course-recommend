package com.course.recommend.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.course.recommend.domain.CustomUser;

@Repository
public class UserService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String GET_USER = "SELECT u.userName,u.password,u.firstName,u.lastName,u.email,u.role,up.photo "
			+ "FROM users u LEFT JOIN userPhoto up ON u.userName = up.userName " + "WHERE u.userName = ? ";

	private static final String INSERT_USER = "INSERT INTO USERS(USERNAME, PASSWORD, ENABLED, FIRSTNAME, LASTNAME, EMAIL, ROLE) "
			+ "VALUES(?,?,?,?,?,?,?)";

	private static final String UPDATE_PHOTO = "UPDATE userPhoto SET photo = ? WHERE userName = ?";

	private static final String UPDATE_USER = "UPDATE users SET firstname = ?, lastname = ?, email = ? WHERE userName = ?";

	private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE userName = ?";

	private static final String GET_USERS_BY_TYPE = "SELECT u.userName,u.password,u.firstName,u.lastName,u.email,u.role,up.photo "
			+ "FROM users u LEFT JOIN userPhoto up ON u.userName = up.userName " + "WHERE u.role = ? ";

	public CustomUser getUser(String username) {
		CustomUser user = (CustomUser) jdbcTemplate.queryForObject(GET_USER, new Object[] { username },
				new RowMapper<CustomUser>() {
					public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
						GrantedAuthority authority = new SimpleGrantedAuthority(rs.getString("role"));
						CustomUser user = new CustomUser(rs.getString("username"), rs.getString("password"),
								rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"),
								rs.getBytes("photo"), Arrays.asList(authority));
						return user;
					}
				});

		return user;
	}

	public void insertUser(CustomUser user) {
		jdbcTemplate.update(INSERT_USER, user.getUsername(), user.getPassword(), 1, user.getFirstname(),
				user.getLastname(), user.getEmail(), user.getAuthorities().iterator().next().toString());
	}

	private void updatePhoto(String userName, byte[] photo) {
		jdbcTemplate.update(UPDATE_PHOTO, photo, userName);
	}

	public void updateUser(String firstName, String lastName, String email, byte[] photo, CustomUser currentUser) {

		if (photo != null)
			updatePhoto(currentUser.getUsername(), photo);

		jdbcTemplate.update(UPDATE_USER, firstName, lastName, email, currentUser.getUsername());

	}

	public void updatePassword(String userName, String newPassword) {

		jdbcTemplate.update(UPDATE_PASSWORD, newPassword, userName);

	}

	public List<CustomUser> getUsersByRole(String role) {
		List<CustomUser> students = jdbcTemplate.query(GET_USERS_BY_TYPE, new Object[] { role }, new RowMapper<CustomUser>() {
			public CustomUser mapRow(ResultSet rs, int i) throws SQLException {
				GrantedAuthority authority = new SimpleGrantedAuthority(rs.getString("role"));
				CustomUser user = new CustomUser(rs.getString("username"), rs.getString("password"),
						rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"),
						rs.getBytes("photo"), Arrays.asList(authority));
				return user;
			}
		});
		return students;
	}

}
