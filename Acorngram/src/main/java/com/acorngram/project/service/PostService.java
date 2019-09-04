package com.acorngram.project.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dto.PostDto;


public interface PostService {
	
	//Post List 처리 Logic 
	public void getList(HttpServletRequest request);
	//파일 삭제 비즈니스 로직 처리 
	public boolean removePostInfo(int num, HttpServletRequest request, HttpServletResponse response);
	//파일 저장 비즈니스 로직 처리 
	public void savePost(PostDto dto, HttpServletRequest request);
	//파일 정보 가져오는 비즈니스 로직 처리 
	public void getPostData(int num, HttpServletRequest request, ModelAndView mView);
	
	//like_count +1 -> like 하면
	public void increaseLikeCount(int num);
	
	//like_count -1 -> unlike하면
	public void decreaseLikeCount(int num);
	
}
