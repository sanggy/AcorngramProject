package com.acorngram.project.dao;

import java.util.List;

import com.acorngram.project.dto.CommentDto;

public interface CommentDao {
	//post의 댓글 목록 가지고 오기
	public List<CommentDto> getList(int ref_group);
	
	//댓글 삭제
	public void delete(int num);
	
	//댓글 추가
	public void insert(CommentDto dto);
	
	//추가할 댓글의 pk값을 얻어오기
	public int getSequence();
	
	//댓글을 수정
	public void deleteAll(int post_num);
	
	//get 댓글 count
	public int getCount(int ref_group);
}
