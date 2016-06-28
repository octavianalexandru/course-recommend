package com.course.recommend.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.course.recommend.domain.CustomUser;
import com.course.recommend.service.FileUploadService;
import com.course.recommend.service.UserService;
import com.course.recommend.util.CustomUserValidator;

@Controller
public class RegisterController {

	private final UserService userService;
	private final FileUploadService fileUploadService;
	private final CustomUserValidator customUserValidator;

	@Autowired
	public RegisterController(UserService userService, CustomUserValidator customUserValidator,
			FileUploadService fileUploadService) {
		this.userService = userService;
		this.customUserValidator = customUserValidator;
		this.fileUploadService = fileUploadService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("register");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "role", required = false) String role,
			@RequestParam(value = "photo", required = false) MultipartFile photo) {

		ModelAndView model = new ModelAndView();

		Set<GrantedAuthority> auth = new HashSet<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(role));

		byte[] bytes = null;

		if (!photo.isEmpty()) {
			try {
				bytes = photo.getBytes();
			} catch (Exception e) {
				bytes = null;
			}
		}
		
		

		CustomUser user = new CustomUser(userName, password, firstName, lastName, email, bytes, auth);

		List<String> errors = customUserValidator.validate(user);

		if (errors.isEmpty()) {
			userService.insertUser(user);
			fileUploadService.insertPhoto(bytes, user.getUsername());
			if(role.equals("ROLE_TEACHER")){
				model.setViewName("login");
			}
			else if(role.equals("ROLE_USER")){
				model.addObject("username", userName);
				model.setViewName("registerlocation");
			}
		} else {
			model.addObject("error", errors);
			model.setViewName("register");
		}
		return model;
	}
	
	
	
	
	@RequestMapping(value = "/registerlocation", method = RequestMethod.POST)
	public ModelAndView registerUserLocation(HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value= "locationId", required = false) int locationId
			) {
		ModelAndView model = new ModelAndView();
		
		userService.setLocation(username,locationId);
		
		model.addObject("username", username);
		model.setViewName("registereducationalstage");
		return model;
	}
	
	@RequestMapping(value = "/registereducationalstage", method = RequestMethod.POST)
	public ModelAndView registerEducationalStage(HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value= "educationalStageId", required = false) int educationalStageId
			) {
		ModelAndView model = new ModelAndView();
		
		userService.setEducationalStage(username,educationalStageId);
		
		model.addObject("username",username);
		model.setViewName("registerage");
		return model;
	}
	
	@RequestMapping(value = "/registerage", method = RequestMethod.POST)
	public ModelAndView registerAge(HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value= "ageCategoryId", required = false) int ageCategoryId
			) {
		ModelAndView model = new ModelAndView();
		
		userService.setAgeCategory(username,ageCategoryId);
		
		model.setViewName("redirect:/login");
		return model;
	}
	
	
}
