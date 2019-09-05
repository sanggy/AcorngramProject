package com.acorngram.project.dao;

import java.util.List;

import com.acorngram.project.dto.FollowerDto;

public interface FollowerDao {
	
	//follower가 처음에 생기면 follower정보가 추가되는 메소드
	public boolean insert(FollowerDto dto);
	
	//follower list가지고 오는 메소드
	public List<FollowerDto> getList(int selfCode);
	
	//follower가 unfollow하면 사용되는 메소드 -> 유저 follower리스트에서 삭제 메소드
	public boolean delete(FollowerDto dto);
	
	//follower list 가지고 오기
	public List<FollowerDto> followerList(int usercode);
	//following list 가지고 오기
	public List<FollowerDto> followingList(int usercode);
	
}
