package com.acorngram.project.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDaoImpl implements UsersDao{
	@Autowired SqlSession session;
	
	//메소드 만들기
	
}
