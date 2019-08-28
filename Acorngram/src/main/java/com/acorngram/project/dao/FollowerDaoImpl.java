package com.acorngram.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorngram.project.dto.FollowerDto;

@Repository
public class FollowerDaoImpl implements FollowerDao{
	@Autowired SqlSession session;

	@Override
	public void insert(FollowerDto dto) {
		//초기 follower가 생기면 초기 리스트를 생성하는 메소드
		session.insert("follower.insert", dto);
	}

	@Override
	public List<FollowerDto> getList(int selfCode) {
		//follower list를 불러와서 리턴하는 메소드
		return session.selectList("follower.getList", selfCode);
	}

	@Override
	public void update(FollowerDto dto) {
		//초기follower가 추가된 이후 추가되는 follower들을 위한 메소드-> additional followers being added to the list
		session.update("follower.update", dto);
	}

	@Override
	public void delete(FollowerDto dto) {
		//상대 유저가 unfollowe 하면 사용될 메소드
		session.delete("follower.delete", dto);
	}
}
