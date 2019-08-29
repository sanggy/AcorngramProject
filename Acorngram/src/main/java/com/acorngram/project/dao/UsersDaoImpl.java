package com.acorngram.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorngram.project.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	
	@Autowired SqlSession session;

	@Override
	public void insert(UsersDto dto) {
		//회원가입 
		session.insert("users.insert", dto);
	}

	@Override
	public String getPwdHash(String id) {
		//비번 hash된거 불러오기 나중에 로그인 할때 Bcrypt에서 지원하는 메소드랑 함께 사용 될 값.
		return session.selectOne("users.getPwdHash", id);
	}

	@Override
	public UsersDto getData(String id) {
		//마이페이지에 개인정보 불러오기 기능에 사용 될 메소드
		return session.selectOne("users.getData", id);
	}

	@Override
	public void delete(String id) {
		//회원 탈퇴 
		session.delete("users.delete", id);
		
	}

	@Override
	public boolean idCheck(String inputId) {
		//아이디 중복 체크 기능에 사용될 메소드, resultSet의 수가 0보다 크면 존재한다는 뜻이기에 false 리턴..
		int isExist = session.selectOne("users.idCheck", inputId);
		if(isExist > 0) {
			//아이디 중복되니 사용x 라는 의미
			return false;
		}
		//아이디 사용 가능하면 true 리턴.
		return true;
	}

	@Override
	public void updateProfileImg(UsersDto dto) {
		//users 프로필 이미지 변경 
		session.update("users.updateProfileImg", dto);
	}

	@Override
	public void update(UsersDto dto) {
		//users 개인정보 및 dob 변경사항 update
		session.update("users.update", dto);
	}

	@Override
	public void updateSettings(UsersDto dto) {
		//users setting값 변경사항 update
		session.update("users.updateSettings", dto);
	}

	@Override
	public void updatePwd(UsersDto dto) {
		session.update("users.updatePwd", dto);
	}

	@Override
	public List<UsersDto> getList(UsersDto dto) {
		List<UsersDto> list = session.selectList("users.getSimpleData",dto);
		return list;
	}

	@Override
	public int simpleData(String id) {
		return session.selectOne("users.getSimpleData", id);
	}
	
	
	
}
