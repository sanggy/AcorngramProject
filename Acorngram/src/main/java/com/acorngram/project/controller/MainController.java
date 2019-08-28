package com.acorngram.project.controller;

import java.net.URLEncoder;

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
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value="/users/signup.do", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute UsersDto dto, ModelAndView mView) {
		
		usersService.addUser(dto, mView);
		mView.setViewName("users/loginform");
		return mView;
	}
	
	@RequestMapping(value="/users/signin.do", method = RequestMethod.POST)
	public 	ModelAndView signIn(@ModelAttribute UsersDto dto, ModelAndView mView, HttpServletRequest request) {
		
		usersService.validUser(dto, mView, request.getSession());
		//원래 가려던 url 정보를 reqeust 에 담는다.
		String encodedUrl = URLEncoder.encode(request.getParameter("url"));
		request.setAttribute("encodedUrl", encodedUrl);
		mView.setViewName("home");
	
		return mView;	
	}
	
	
	

}//UsersController END
