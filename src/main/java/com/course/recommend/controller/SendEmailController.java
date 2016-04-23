package com.course.recommend.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.course.recommend.service.MailService;
import com.course.recommend.service.UserService;

@Controller
public class SendEmailController {

	private final UserService userService;
	private final MailService mailService;

	@Autowired
	public SendEmailController(UserService userService, MailService mailService) {
		this.userService = userService;
		this.mailService = mailService;

	}

	@RequestMapping(value = "/sendemail", method = RequestMethod.GET)
	public ModelAndView getSendEmail() {
		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<CustomUser> students = userService.getUsersByRole("ROLE_USER");

		model.addObject("user", user);

		model.addObject("students", students);

		model.setViewName("sendemail");

		return model;
	}
	
	@RequestMapping(value = "/getemails", method = RequestMethod.GET)
	public ModelAndView getEmails() {
		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Email> emails = mailService.getLatestEmails(user.getUsername());

		model.addObject("emails", emails);

		List<Email> allEmails = mailService.getAllEmails(user.getUsername());
		
		model.addObject("user", user);
		
		model.addObject("allEmails", allEmails);

		model.setViewName("myemails");

		return model;
	}


	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public ModelAndView sendEmail(HttpServletRequest request,
			@RequestParam(value = "emails", required = false) String recipients,
			@RequestParam(value = "emailSubject", required = false) String subject,
			@RequestParam(value = "emailMessage", required = false) String textMessage) {

		ModelAndView model = new ModelAndView();

		List<String> recipientList = new ArrayList<String>(Arrays.asList(recipients.split(",")));

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<CustomUser> students = userService.getUsersByRole("ROLE_USER");

		for (String current : recipientList) {
			Email email = new Email(user.getUsername(), user.getFirstname(), user.getLastname(), current, subject,
					textMessage, null);
			mailService.insertEmail(email);
		}

		model.addObject("user", user);

		model.addObject("students", students);

		model.setViewName("sendemail");

		return model;
	}
}
