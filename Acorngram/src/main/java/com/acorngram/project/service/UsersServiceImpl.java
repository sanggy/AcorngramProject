package com.acorngram.project.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dao.UsersDao;
import com.acorngram.project.dto.UsersDto;

@Repository
public class UsersServiceImpl implements UsersService{
	
	
	@Autowired
	private UsersDao dao;
	
	

	// 회원 정보 DB 저장 
	@Override
	public void addUser(UsersDto dto, ModelAndView mView) {
		
		// 암호화 후에 Dto에 객체 담고 DB에 저장 
		String rawPwd = dto.getPw();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		dto.setPw(encoder.encode(rawPwd));
		dao.insert(dto);
	}

	// 로그인 시 아이디 유무 체크 
	@Override
	public void validUser(UsersDto dto, ModelAndView mView, HttpSession session) {
		//아이디 비밀번호가 유효한지 여부
		boolean isValid = false;
		
		String pwdHash = dao.getPwdHash(dto.getId());
		
		//만일 아이디에 해당하는 비밀번호가 존재한다면
		if(pwdHash != null) {
			//비번 일치 여부를 얻어낸다
			
			isValid = BCrypt.checkpw(dto.getPw(), pwdHash);
		}
		
		if(isValid) {
			//로그인 처리를 한다
			session.setAttribute("id", dto.getId());
			session.setAttribute("usercode", dto.getUsercode());
			mView.addObject("isSuccessful", true);
		}else {
			mView.addObject("isSuccessful", false);
		}
		
	}

	// 회원 정보 
	@Override
	public void showInfo(HttpSession session, ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}

	// 아이디 중복 체크 
	@Override
	public Map<String, Object> isExistId(String inputId) {
		
		boolean isExist = dao.idCheck(inputId);
		Map<String,	Object> map = new HashMap<>();
		map.put("istExist", isExist);
		
		return map;
	}

	// 회원 정보 삭제 
	@Override
	public void deleteUser(HttpSession session) {
		String id = (String)session.getAttribute("id");
		dao.delete(id);
		session.invalidate();
		
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
