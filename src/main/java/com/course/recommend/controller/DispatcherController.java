package com.course.recommend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.course.recommend.domain.CustomUser;
import com.course.recommend.domain.Email;
import com.course.recommend.service.MailService;

@Controller
public class DispatcherController {

	private final MailService mailService;

	@Autowired
	public DispatcherController(MailService mailService) {
		this.mailService = mailService;

	}

	@RequestMapping(value = "/dispatch", method = RequestMethod.GET)
	public ModelAndView dispatch() {

		ModelAndView model = new ModelAndView();

		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		boolean authorized = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

		model.addObject("user", user);

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
