package com.acorngram.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dao.FollowerDao;
import com.acorngram.project.dto.FollowerDto;

@Repository
public class FollowerServiceImpl implements FollowerService{
	
	@Autowired FollowerDao dao;
	
	@Override
	public boolean follow(int userCode, HttpServletRequest request) {
		FollowerDto followerDto = new FollowerDto();
		int self_userCode = (int)request.getSession().getAttribute("usercode");
		followerDto.setSelf_usercode(self_userCode);
		followerDto.setTarget_usercode(userCode);
		followerDto.setStatus(1);
		boolean isAdded = dao.insert(followerDto);
		return isAdded;
	}

	@Override
	public List<FollowerDto> followerList(int selfCode) {
		return dao.getList(selfCode);
	}


	@Override
	public boolean unfollow(int target_userCode, HttpServletRequest request) {
		FollowerDto followerDto = new FollowerDto();
		int self_userCode = (int)request.getSession().getAttribute("usercode");
		followerDto.setSelf_usercode(self_userCode);
		followerDto.setTarget_usercode(target_userCode);
		boolean isRemoved = dao.delete(followerDto);
		System.out.println("isRemoved VALUE CHECKER : "+isRemoved);
		return isRemoved;
	}

	@Override
	public int getFollowingCount() {
		return dao.followingCount();
	}

}
