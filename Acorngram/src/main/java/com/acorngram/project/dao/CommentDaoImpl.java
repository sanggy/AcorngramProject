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
		return session.selectList("comment.getList", ref_group);
	}

	@Override
	public void delete(int num) {
		session.delete("comment.delete", num);
	}

	@Override
	public void insert(CommentDto dto) {
		session.insert("comment.insert", dto);
	}

	@Override
	public int getSequence() {
		return session.selectOne("comment.getSequence");
	}

	@Override
	public void update(CommentDto dto) {
		session.update("comment.update", dto);
	}
}
