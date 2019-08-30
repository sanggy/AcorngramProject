package com.acorngram.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorngram.project.dto.PostDto;

@Repository
public class PostDaoImpl implements PostDao{
	@Autowired SqlSession session;

	@Override
	public void insert(PostDto dto) {
		//post를 만드는 메소드
		session.insert("post.insert", dto);
	}

	@Override
	public List<PostDto> getList(PostDto dto) {
		//전체 post리스트를 불러오고 리턴해주는 메소드
		return session.selectList("post.getList", dto);
	}

	@Override
	public PostDto getData(int num) {
		// 1. post 삭제 시 post 정보 가지고 올떄 사용
		// 2. post를 선택하면 다른 뷰페이지에 보이기위해 사용될 post 하나 불러오는 메소드
		return session.selectOne("post.getData", num);
	}

	@Override
	public void update(PostDto dto) {
		//post 내용을 수정하는 메소드
		session.update("post.update", dto);
	}

	@Override
	public void delete(int num) {
		//post를 삭제 하는 메소드
		session.delete("post.delete", num);
	}

	@Override
	public int getCount(PostDto dto) {
		//페이징 처리 (pagination)아니면 scrolling처리 할 때에 총 수를 가지고 오는 메소드
		return session.selectOne("post.getCount", dto);
	}
}
