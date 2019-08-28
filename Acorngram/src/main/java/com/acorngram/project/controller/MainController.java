package com.acorngram.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dto.UsersDto;
import com.acorngram.project.service.UsersService;

@Controller
public class MainController {
	@Autowired UsersService users_service;
	
	
	@RequestMapping(value = "/users/signin", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute UsersDto dto, ModelAndView mView, HttpSession session, HttpServletRequest request) {
		users_service.validUser(dto, mView, session);
		mView.setViewName("home");
		return mView;
	}
	
	
	
}
