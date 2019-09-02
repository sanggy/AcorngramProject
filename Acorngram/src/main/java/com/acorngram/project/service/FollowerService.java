package com.acorngram.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dto.FollowerDto;

public interface FollowerService {
	//유저가 상대방을 follow 하는 기능
	public boolean follow(int target_userCode, HttpServletRequest request);
	
	//follower list를 가지고 오는 서비스
	public List<FollowerDto> followerList(int selfCode);
	
	//unfollow 하는 서비스
	public boolean unfollow(int target_userCode, HttpServletRequest request);
}
