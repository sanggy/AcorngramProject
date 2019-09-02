package com.acorngram.project.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorngram.project.dao.LikesDao;
import com.acorngram.project.dto.LikedDto;

@Service
public class LikesServiceImpl implements LikesService{
	@Autowired LikesDao likesDao;
	
	@Override
	public void likePost(LikedDto dto, HttpServletRequest request) {
		likesDao.likePost(dto);
	}

	@Override
	public void unlikePost(LikedDto dto, HttpServletRequest request) {
		likesDao.unlikePost(dto);
	}

	@Override
	public void getLikeList(LikedDto dto, HttpServletRequest request) {
		
		likesDao.getLikedPost(dto);
		
	}

}
