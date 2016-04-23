package com.course.recommend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.course.recommend.domain.CourseType;
import com.course.recommend.domain.CustomUser;
import com.course.recommend.service.CourseTypeService;
import com.course.recommend.service.StatisticsService;

@Controller
public class StatisticsController {

	private final CourseTypeService courseTypeService;
	private final StatisticsService statisticsService;

	@Autowired
	public StatisticsController(CourseTypeService courseTypeService, StatisticsService statisticsService) {

		this.courseTypeService = courseTypeService;
		this.statisticsService = statisticsService;

	}

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ModelAndView getCourses() {

		ModelAndView model = new ModelAndView();

		Map<String, Integer> courseStatistics = new TreeMap<String, Integer>();
		
		List<String> colors = new ArrayList<String>();
		colors.add("red");
		colors.add("gray");
		colors.add("yellow");
		colors.add("green");
		colors.add("orange");
		colors.add("blue");

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		int numberOfCourses = statisticsService.getNumberOfCourses();
		int myCourses = statisticsService.getCoursesByUser(user.getUsername());
		int numberOfStudents = statisticsService.getNumberOfStudents();
		int myStudents = statisticsService.getStudentsByUser(user.getUsername());

		List<CourseType> allTypes = courseTypeService.getAllTypes();

		for (CourseType current : allTypes)
			courseStatistics.put(current.getDescription(),
					statisticsService.getNumberOfCoursesByTypeAndUser(current.getId(), user.getUsername()));

		model.addObject("user", user);

		model.addObject("numberOfCourses", numberOfCourses);

		model.addObject("myCourses", myCourses);

		model.addObject("numberOfStudents", numberOfStudents);

		model.addObject("myStudents", myStudents);

		model.addObject("courseStatistics", courseStatistics);
		
		model.addObject("colors", colors);

		model.setViewName("statistics");

		return model;
	}
}
