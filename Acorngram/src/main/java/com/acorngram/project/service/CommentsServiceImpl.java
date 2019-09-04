package com.acorngram.project.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorngram.project.dao.CommentDao;
import com.acorngram.project.dto.CommentDto;

@Service
public class CommentsServiceImpl implements CommentsService{
	@Autowired CommentDao commentsDao;

	@Override
	public void writeComment(HttpServletRequest request, CommentDto dto) {
		//댓글 작성자 -일단 usercode로 한다
		int usercode = (int)(request.getSession().getAttribute("usercode"));
		
		//댓글의 그룹 번호
		int ref_group = Integer.parseInt(request.getParameter("ref_group"));
		//댓글의 대상자 아이디
		int target_code = Integer.parseInt(request.getParameter("target_code"));
		//댓글의 내용
		String content = request.getParameter("content");
		//댓글 내에서의 그룹번호 (null이면 원글의 댓글이다)
		String comment_group = request.getParameter("comment_group");
		
		//저장할 댓글의 primary key값이 필요하다
		int seq = commentsDao.getSequence();
		//댓글 정보를 Dto에 담아서 보내기
		dto.setNum(seq);
		dto.setUsercode(usercode);
		dto.setTarget_code(target_code);
		dto.setRef_group(ref_group);
		dto.setContent(content);
		
		if(comment_group == null) {
			dto.setComment_group(seq);
		}else {
			dto.setComment_group(Integer.parseInt(request.getParameter("comment_group")));
		}
		commentsDao.insert(dto);
	}

	@Override
	public void deleteComment(int num) {
		commentsDao.delete(num);
	}

	@Override
	public void getCount(HttpServletRequest request, int ref_group) {
		commentsDao.getCount(ref_group);
	}
	
	
	
}
