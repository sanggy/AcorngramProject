package com.acorngram.project.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dto.UsersDto;

public interface DirectMessageService {
	//user 정보 불러오는 메소드
	public void userProfile(int usercode, HttpServletRequest req, ModelAndView mView);
	
}
