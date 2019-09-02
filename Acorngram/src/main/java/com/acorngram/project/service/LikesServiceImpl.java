package com.acorngram.project.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorngram.project.dao.LikesDao;
import com.acorngram.project.dao.PostDao;
import com.acorngram.project.dto.LikedDto;
import com.acorngram.project.dto.PostDto;

@Service
public class LikesServiceImpl implements LikesService{
	@Autowired LikesDao likesDao;
	@Autowired PostDao postDao;
	
	@Override
	public void likePost(LikedDto dto, HttpServletRequest request) {
//		dto.setPost_num(Integer.parseInt(request.getParameter("num")));
		dto.setUser_code((int)request.getSession().getAttribute("usercode"));
		likesDao.likePost(dto);
	}

	@Override
	public void unlikePost(LikedDto dto, HttpServletRequest request) {
		dto.setPost_num(Integer.parseInt(request.getParameter("num")));
		dto.setUser_code((int)request.getSession().getAttribute("usercode"));
		likesDao.unlikePost(dto);
	}

	@Override
	public void getLikeList(LikedDto dto, HttpServletRequest request) {
		likesDao.getLikedPost(dto);
		
	}

	@Override
	public void increaseLikeCount(HttpServletRequest request) {
		likesDao.increaseLikeCount(request);
	}

}
