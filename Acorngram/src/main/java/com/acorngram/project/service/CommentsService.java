package com.acorngram.project.service;

import javax.servlet.http.HttpServletRequest;

import com.acorngram.project.dto.CommentDto;

public interface CommentsService {
	//댓글 달기
	public void writeComment(HttpServletRequest request, CommentDto dto);
	
	//댓글 삭제
	public void deleteComment(int num);
	
	//댓글 수정
	
}
