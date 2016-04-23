package com.course.recommend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.course.recommend.domain.CustomUser;
import com.course.recommend.domain.Email;
import com.course.recommend.domain.FullCourse;
import com.course.recommend.service.CourseService;
import com.course.recommend.service.MailService;

@Controller
public class SearchController {

	private final CourseService courseService;
	private final MailService mailService;

	@Autowired
	public SearchController(CourseService courseService, MailService mailService) {
		this.courseService = courseService;
		this.mailService = mailService;

	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView getCourses(HttpServletRequest request,
			@RequestParam(value = "searchText", required = false) String searchText) {

		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<FullCourse> allCourses = courseService.getCoursesBySearchText(searchText);

		model.addObject("user", user);

		model.addObject("allCourses", allCourses);

		List<Email> emails = mailService.getLatestEmails(user.getUsername());

		model.addObject("emails", emails);

		model.setViewName("search");
		
		return model;
	}

}
