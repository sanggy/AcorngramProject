package com.acorngram.project.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dao.UsersDao;
import com.acorngram.project.dto.UsersDto;

@Service
public class DirectMessageServiceImpl implements DirectMessageService{
	
	@Autowired UsersDao usersDao;

	@Override
	public void userProfile(int usercode, HttpServletRequest req, ModelAndView mView) {
		UsersDto user = usersDao.getUserData((int)req.getSession().getAttribute("usercode"));
		UsersDto targetUser = usersDao.getUserData(usercode);
		mView.addObject("user", user);
		mView.addObject("targetUser", targetUser);
	}
	
	

}
