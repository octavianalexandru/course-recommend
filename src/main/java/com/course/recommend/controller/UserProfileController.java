package com.course.recommend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.course.recommend.domain.CustomUser;
import com.course.recommend.domain.Email;
import com.course.recommend.service.MailService;
import com.course.recommend.service.UserService;

@Controller
public class UserProfileController {

	private final UserService userService;
	private final MailService mailService;
	
	@Autowired
	public UserProfileController(UserService userService,MailService mailService) {
		this.userService = userService;
		this.mailService = mailService;

	}

	@RequestMapping(value = "/viewprofile", method = RequestMethod.GET)
	public ModelAndView viewProfile() {
		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addObject("user", user);
		
		List<Email> emails = mailService.getLatestEmails(user.getUsername());

		model.addObject("emails", emails);

		model.setViewName("profile/viewprofile");

		return model;
	}

	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addObject("user", user);
		
		List<Email> emails = mailService.getLatestEmails(user.getUsername());

		model.addObject("emails", emails);

		model.setViewName("profile/editprofile");

		return model;
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public ModelAndView changePassword() {
		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addObject("user", user);
		
		List<Email> emails = mailService.getLatestEmails(user.getUsername());

		model.addObject("emails", emails);

		model.setViewName("profile/changepassword");

		return model;
	}

	@RequestMapping(value = "/editprofile", method = RequestMethod.POST)
	public ModelAndView saveProfile(HttpServletRequest request,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "email", required = false) String email,
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
		
		userService.updateUser(firstName, lastName, email, bytes, user);

		return new ModelAndView("redirect:/dispatch");

	}
	
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public ModelAndView updatePassword(HttpServletRequest request,
			@RequestParam(value = "currentPassword", required = false) String currentPassword,
			@RequestParam(value = "newPassword", required = false) String newPassword,
			@RequestParam(value = "retypeNewPassword", required = false) String retypeNewPassword) {
		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ArrayList<String> errors = new ArrayList<String>();
		
		if(!newPassword.equals(retypeNewPassword))
			errors.add("Please re-type the password correctly !");
		if(!currentPassword.equals(user.getPassword()))
			errors.add("Please type your password correctly !");
		
		model.addObject("user", user);
		
		if(errors.isEmpty())
			userService.updatePassword(user.getUsername(), newPassword);
		else
			model.addObject("error", errors);
			
		model.setViewName("profile/changepassword");

		return model;
	}

}
