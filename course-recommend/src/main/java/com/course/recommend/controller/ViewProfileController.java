package com.course.recommend.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class ViewProfileController {

	private final UserService userService;

	@Autowired
	public ViewProfileController(UserService userService) {
		this.userService = userService;

	}

	@RequestMapping(value = "/viewprofile", method = RequestMethod.GET)
	public ModelAndView viewProfile() {
		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addObject("user", user);

		model.setViewName("viewprofile");

		return model;
	}

	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addObject("user", user);

		model.setViewName("editprofile");

		return model;
	}

	@RequestMapping(value = "/saveprofile", method = RequestMethod.POST)
	public ModelAndView saveProfile(HttpServletRequest request,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "photo", required = false) MultipartFile photo) {

		ModelAndView model = new ModelAndView();

		byte[] bytes = null;

		if (!photo.isEmpty()) {
			try {
				bytes = photo.getBytes();
			} catch (Exception e) {
				bytes = null;
			}
		}

		return model;

	}
}
