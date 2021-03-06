package com.acorngram.project.dao;

import java.util.List;

import com.acorngram.project.dto.PostDto;

public interface PostDao {
	//post작성
	public void insert(PostDto dto);
	
	//post full list가지고 오는 메소드
	public List<PostDto> getList(PostDto dto);
	
	//get user's post list (from profile page)
	public List<PostDto> getMyList(int usercode);
	
	//post 하나만 불러오기
	public PostDto getData(int num);
	
	//수저 메소드
	public void update(PostDto dto);
	
	//삭제 메소드
	public int delete(int num);
	
	//전체 post의 갯수 (나중에 페이지 처리할 때 사용할 메소드)
	public int getCount(int usercode);
	
	//like_count 올리기
	public void incraseLikeCount(int num);
	//like_count 줄이기
	public void decreaseLikeCount(int num);
	
}
