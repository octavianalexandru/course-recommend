package com.course.recommend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.course.recommend.domain.CourseType;
import com.course.recommend.domain.CustomUser;
import com.course.recommend.domain.Email;
import com.course.recommend.domain.FullCourse;
import com.course.recommend.service.CourseService;
import com.course.recommend.service.CourseTypeService;
import com.course.recommend.service.MailService;
import com.course.recommend.service.RecommendService;
import com.course.recommend.service.RegisterService;
import com.course.recommend.service.UserService;

@Controller
public class CourseController {

	private final CourseService courseService;
	private final CourseTypeService courseTypeService;
	private final MailService mailService;
	private final RecommendService recommendService;
	private final RegisterService registerService;
	private final UserService userService;
	
	
	@Autowired
	public CourseController(CourseService courseService, CourseTypeService courseTypeService, MailService mailService,
			RecommendService recommendService, RegisterService registerService, UserService userService) {
		this.courseService = courseService;
		this.courseTypeService = courseTypeService;
		this.mailService = mailService;
		this.recommendService = recommendService;
		this.registerService = registerService;
		this.userService = userService;
	}

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public ModelAndView getCourses() {

		ModelAndView model = new ModelAndView();

		Map<String, List<FullCourse>> coursesByType = new TreeMap<String, List<FullCourse>>();
		Map<Integer, Double> courseAverageRatings = new HashMap<Integer, Double>();
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<FullCourse> allCourses = courseService.getAllCourses();

		model.addObject("user", user);

		List<CourseType> allTypes = courseTypeService.getAllTypes();
		
		Map<Integer,Integer> courseRatings = new HashMap<Integer,Integer>();
		
		for (int i = 0; i < allTypes.size(); i++)
			coursesByType.put(allTypes.get(i).getDescription(), new ArrayList<FullCourse>());

		for (int i = 0; i < allCourses.size(); i++){
			coursesByType.get(allCourses.get(i).getCourseTypeDescription()).add(allCourses.get(i));
			//courseAverageRatings.put(allCourses.get(i).getId(), courseService.getCourseAverageRating(allCourses.get(i).getId()));
			courseRatings.put(allCourses.get(i).getId(),courseService.getCourseRating(user.getUsername(),allCourses.get(i).getId()));
			
		}
		model.addObject("coursesByType", coursesByType);
		
		List<Email> emails = mailService.getLatestEmails(user.getUsername());

		
		
		
		model.addObject("ratings",courseRatings);
		
		//model.addObject("averageratings", courseAverageRatings);
		
		model.addObject("emails", emails);
		
		model.setViewName("course/courses");

		return model;
	}

	

	@RequestMapping(value = "/mycourses", method = RequestMethod.GET)
	public ModelAndView getMyCourses() {

		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<FullCourse> allCourses = courseService.getCoursesByUserName(user.getUsername());

		model.addObject("user", user);

		model.addObject("courses", allCourses);

		model.setViewName("course/mycourses");

		return model;
	}

	@RequestMapping(value = "/addcourse", method = RequestMethod.GET)
	public ModelAndView addCourse() {

		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<CourseType> allTypes = courseTypeService.getAllTypes();

		model.addObject("user", user);

		model.addObject("allTypes", allTypes);

		model.setViewName("course/addcourse");

		return model;

	}

	@RequestMapping(value = "/addcourse", method = RequestMethod.POST)
	public ModelAndView saveCourse(HttpServletRequest request,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "numberOfWeeks", required = false) int numberOfWeeks,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "type", required = false) int courseTypeId,
			@RequestParam(value = "photo", required = false) MultipartFile photo) {

		byte[] bytes = null;

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (!photo.isEmpty()) {
			try {
				bytes = photo.getBytes();
			} catch (Exception e) {
				bytes = null;
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date start = null;
		java.util.Date end = null;
		java.util.Date dateEntered = new java.util.Date();
		try {
			start = format.parse(startDate);
			end = format.parse(endDate);
		} catch (Exception e) {
		}
		
		FullCourse fullCourse = new FullCourse(0, numberOfWeeks, title, description, bytes, user.getUsername(),
				new java.sql.Date(start.getTime()), new java.sql.Date(end.getTime()), courseTypeId,
				courseTypeService.getTypeById(courseTypeId).getDescription(), new java.sql.Date(dateEntered.getTime()));

		courseService.insertCourse(fullCourse);

		return new ModelAndView("redirect:/dispatch");

	}

	@RequestMapping(value = "/readmore", method = RequestMethod.POST)
	public ModelAndView getFullCourse(HttpServletRequest request,
			@RequestParam(value = "courseId", required = false) int courseId) {

		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		boolean authorized = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_USER"));

		FullCourse fullCourse = courseService.getFullCourseById(courseId);

		String teacherName = userService.getFullNameByUserName(fullCourse.getUserName());

		if (authorized) {

			int register = registerService.isRegistered(courseId, user.getUsername());

			model.addObject("register", register);

			recommendService.onClick(fullCourse, user.getUsername());

			recommendService.onClickTeacher(fullCourse.getUserName(), user.getUsername());
		}
		
		
		int courseRating=courseService.getCourseRating(user.getUsername(),courseId);
		
		model.addObject("courseRating",courseRating);
		
		model.addObject("user", user);

		model.addObject("teacherName", teacherName);

		model.addObject("fullCourse", fullCourse);

		model.setViewName("course/details");

		return model;

	}

	@RequestMapping(value = "/editcourse", method = RequestMethod.POST)
	public ModelAndView editCourse(HttpServletRequest request,
			@RequestParam(value = "courseId", required = false) int courseId) {

		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		FullCourse fullCourse = courseService.getFullCourseById(courseId);

		model.addObject("user", user);

		model.addObject("fullCourse", fullCourse);

		List<CourseType> allTypes = courseTypeService.getAllTypes();

		model.addObject("allTypes", allTypes);

		model.setViewName("course/editcourse");

		return model;

	}

	@RequestMapping(value = "/savecourse", method = RequestMethod.POST)
	public ModelAndView updateCourse(HttpServletRequest request,
			@RequestParam(value = "courseId", required = false) int courseId,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "numberOfWeeks", required = false) int numberOfWeeks,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "type", required = false) int courseTypeId,
			@RequestParam(value = "photo", required = false) MultipartFile photo) {

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		byte[] bytes = null;

		if (!photo.isEmpty()) {
			try {
				bytes = photo.getBytes();
			} catch (Exception e) {
				bytes = null;
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date start = null;
		java.util.Date end = null;
		try {
			start = format.parse(startDate);
			end = format.parse(endDate);
		} catch (Exception e) {
		}

		FullCourse fullCourse = new FullCourse(courseId, numberOfWeeks, title, description, bytes, user.getUsername(),
				new java.sql.Date(start.getTime()), new java.sql.Date(end.getTime()), courseTypeId,
				courseTypeService.getTypeById(courseTypeId).getDescription(), null);

		courseService.updateCourse(fullCourse);

		return new ModelAndView("redirect:/dispatch");

	}

	@RequestMapping(value = "/register/{courseId}")
	public ModelAndView registerToCourse(@PathVariable int courseId) {

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		registerService.register(courseId, user.getUsername());

		FullCourse course = courseService.getFullCourseById(courseId);

		registerService.increment(course.getCourseTypeId(), user.getUsername());
		
		registerService.incrementTeacher(course.getUserName(), user.getUsername());
		
		return new ModelAndView("redirect:/dispatch");
	}

	@RequestMapping(value = "/unregister/{courseId}")
	public ModelAndView unregisterCourse(@PathVariable int courseId) {

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		registerService.unregister(courseId, user.getUsername());

		FullCourse course = courseService.getFullCourseById(courseId);

		registerService.decrement(course.getCourseTypeId(), user.getUsername());
		
		registerService.decrementTeacher(course.getUserName(), user.getUsername());

		return new ModelAndView("redirect:/dispatch");
	}
	
	@RequestMapping(value = "/ratecourse", method = RequestMethod.POST)
	public ModelAndView rateCourse(HttpServletRequest request,
			@RequestParam(value = "rating", required = true) int rating,
			@RequestParam(value= "courseId", required=true) int courseId){
			
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		
		courseService.insertUpdateRating(user.getUsername(),courseId,rating);
		
		
		return new ModelAndView("redirect:/courses");
	}
	
	@RequestMapping(value = "/newadditions", method = RequestMethod.GET)
	public ModelAndView newAdditions(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		
		Map<Date, ArrayList<FullCourse>> newAddedCoursesWithDates = courseService.getNewAddedCourses();
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		model.addObject("newAddedCoursesWithDates", newAddedCoursesWithDates);
		model.setViewName("NewAdditions");
		model.addObject("user", user);
		
		return model;
	}
}
