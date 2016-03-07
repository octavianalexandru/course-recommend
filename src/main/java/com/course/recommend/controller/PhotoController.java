package com.course.recommend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.course.recommend.service.FileUploadService;

@Controller
public class PhotoController {

	private final FileUploadService fileUploadService;

	@Autowired
	public PhotoController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@RequestMapping(value = "/photo/{userName}")
	@ResponseBody
	public HttpEntity<byte[]> getPhoto(@PathVariable String userName) {

		byte[] imageByte = null;

		try {
			imageByte = fileUploadService.getPhoto(userName);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new HttpEntity<byte[]>(imageByte, headers);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/cover/{courseId}")
	@ResponseBody
	public HttpEntity<byte[]> getCover(@PathVariable int courseId) {

		byte[] imageByte = null;

		try {
			imageByte = fileUploadService.getCover(courseId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new HttpEntity<byte[]>(imageByte, headers);
		} catch (Exception e) {
			return null;
		}
	}
}
