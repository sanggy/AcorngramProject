package com.acorngram.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorngram.project.dto.CommentDto;

@Repository
public class CommentDaoImpl implements CommentDao{
	@Autowired SqlSession session;

	@Override
	public List<CommentDto> getList(int ref_group) {
		return session.selectList("comments.getList", ref_group);
	}

	@Override
	public void delete(int num) {
		session.delete("comments.delete", num);
	}

	@Override
	public void insert(CommentDto dto) {
		session.insert("comments.insert", dto);
	}

	@Override
	public int getSequence() {
		return session.selectOne("comments.getSequence");
	}

	@Override
	public void update(CommentDto dto) {
		session.update("comments.update", dto);
	}
}
