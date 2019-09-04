package com.acorngram.project.service;


import javax.servlet.http.HttpServletRequest;

import com.acorngram.project.dto.LikedDto;

public interface LikesService {
	//likeing post
	public void likePost(LikedDto dto, HttpServletRequest request);
	public void unlikePost(LikedDto dto, HttpServletRequest request);
	public void getLikeList(LikedDto dto, HttpServletRequest request);
}
