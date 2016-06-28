package com.course.recommend.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.course.recommend.domain.Course;
import com.course.recommend.domain.CustomUser;
import com.course.recommend.domain.Email;
import com.course.recommend.domain.FullCourse;
import com.course.recommend.service.CourseService;
import com.course.recommend.service.MailService;

@Controller
public class DispatcherController {

	private final MailService mailService;
	private final CourseService courseService;
	
	@Autowired
	public DispatcherController(MailService mailService,CourseService courseService) {
		this.mailService = mailService;
		this.courseService = courseService;
	}

	@RequestMapping(value = "/dispatch", method = RequestMethod.GET)
	public ModelAndView dispatch() {

		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		boolean authorized = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

		model.addObject("user", user);

		List<Integer> topRatedCourseIDs = new ArrayList<Integer>();
		List<FullCourse> topRatedCourses = new ArrayList<FullCourse>();
		topRatedCourseIDs = courseService.getTopRatedCourses();
		
		for (Integer topRatedCourseId : topRatedCourseIDs) {
			topRatedCourses.add(courseService.getFullCourseById(topRatedCourseId));
		}
		model.addObject("topRatedCourses", topRatedCourses);
		if (authorized)
			model.setViewName("admin");
		else{
			List<Email> emails = mailService.getLatestEmails(user.getUsername());
			model.addObject("emails", emails);
			model.setViewName("user");
		}
		return model;
	}
}
