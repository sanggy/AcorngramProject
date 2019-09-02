package com.acorngram.project.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorngram.project.dto.LikedDto;

@Repository
public class LikesDaoImpl implements LikesDao{
	@Autowired SqlSession session;
	
	@Override
	public void likePost(LikedDto dto) {
		session.insert("likes.likePost", dto);
	}

	@Override
	public void unlikePost(LikedDto dto) {
		session.update("likes.postUnlike",dto);
	}

	@Override
	public int getLikedPost(LikedDto dto) {
		return session.selectOne("likes.likedPost", dto);
	}

}
