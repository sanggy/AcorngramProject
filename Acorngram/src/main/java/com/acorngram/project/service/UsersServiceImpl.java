package com.acorngram.project.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dao.UsersDao;
import com.acorngram.project.dto.UsersDto;

@Repository
public class UsersServiceImpl implements UsersService{
	@Autowired UsersDao dao;
	
	

	// 회원 정보 DB 저장 
	@Override
	public void addUser(UsersDto dto, ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}

	// 로그인 시 아이디 유무 체크 
	@Override
	public void validUser(UsersDto dto, ModelAndView mView, HttpSession session) {
		// TODO Auto-generated method stub
		
	}

	// 회원 정보 
	@Override
	public void showInfo(HttpSession session, ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}

	// 아이디 중복 체크 
	@Override
	public Map<String, Object> isExistId(String inputId) {
		// TODO Auto-generated method stub
		return null;
	}

	// 회원 정보 삭제 
	@Override
	public void deleteUser(HttpSession session) {
		// TODO Auto-generated method stub
		
	}

	//  프로 파일 이미지를 저장하는 메서드 
	@Override
	public String saveProfileImage(HttpServletRequest request, MultipartFile mFile) {
		// TODO Auto-generated method stub
		return null;
	}

	// 개인정보 수정 반영하는 메소드
	@Override
	public void updateUser(UsersDto dto) {
		// TODO Auto-generated method stub
		
	}
}
