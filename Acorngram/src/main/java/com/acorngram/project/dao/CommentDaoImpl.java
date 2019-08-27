package com.acorngram.project.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl implements CommentDao{
	@Autowired SqlSession session;
}
