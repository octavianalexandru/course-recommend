package com.course.recommend.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.course.recommend.domain.CustomUser;
import com.course.recommend.service.UserService;

@Component
public class CustomUserValidator {

	private final UserService userService;

	@Autowired
	public CustomUserValidator(UserService userService) {
		this.userService = userService;
	}

	public List<String> validate(CustomUser user) {

		List<String> errors = new ArrayList<String>();
		try {
			if (userService.getUser(user.getUsername()) != null)
				errors.add("User name is already used !");
		} catch (EmptyResultDataAccessException e) {

		} finally {
			return errors;
		}

	}

}
