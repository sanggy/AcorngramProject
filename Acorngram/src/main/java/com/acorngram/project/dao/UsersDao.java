package com.acorngram.project.dao;

import com.acorngram.project.dto.UsersDto;

public interface UsersDao {
	//회원 정보를 저장하는 메소드 -> 회원가입
	public void insert(UsersDto dto);
	
	//암호화된 비번 리턴해주는 메소드
	public String getPwdHash(String id);
	
	//인자로 전달된 아이디에 해당한는 개인정보 리턴
	public UsersDto getData(String id);
	
	//회원 정보를 삭제
	public void delete(String id);
	
	//아이디가 존재하는지 여부를 리턴-> 나중에 아이디중복 체크 기능에 사용 할 메소드
	public boolean idCheck(String inputId);
	
	//프로파일 이미지 리턴 -> profile_img 만...
	public void updateProfileImg(UsersDto dto);
	
	//회원정보 수정 -> 개인 정보만... nickname, email, pw, dob...
	public void update(UsersDto dto);
	
	//회원 settings 수정 메소드 -> dm_range, acc_private, and theme will be changed here
	public void updateSettings(UsersDto dto);
	
	//회원 비번 수정 메소드
	public void updatePwd(UsersDto dto);
	
	
}
