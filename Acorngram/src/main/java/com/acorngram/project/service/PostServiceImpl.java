package com.acorngram.project.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dao.PostDao;
import com.acorngram.project.dao.UsersDao;
import com.acorngram.project.dto.PostDto;
import com.acorngram.project.dto.UsersDto;

@Repository
public class PostServiceImpl implements PostService {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private PostDao postDao;
	
	
	// 리스트 불러오기 
	@Override
	public void getList(HttpServletRequest request) {
		
		/*
		 *  request 에 검색 keyword 가 전달 될 수도 있고 안될 수도 있다.
		 *  1. 전달 안되는 경우 : home에서 바로 로그인한 경우 
		 *  2. 전달 되는 경우 : list.do 에서 검색어를 입력 후 form 전송한 경우 
		 *  3. 전달 되는 경우 : 이미 검색을 한 상태에서 스크롤 다운하는 경우 
		 *  
		 */
		
		// HEADER.JSP 에서 Parameter 불러오기  
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		
		// 검색 키워드가 존재한다면 키워드를 담을 dto 객체 생성
		UsersDto usersDto = new UsersDto();
		PostDto postDto = new PostDto();
		
		// Search된 keyword, condition 분류 및 분류된 값을 담을 dto 객체 생성
		// both, usersname , contentsname  (id or nickname)
	
		if(condition != null) {
		
			if(keyword != null) {
				if(!condition.equals("contentsname")) {
					usersDto.setId(keyword);
					usersDto.setNickname(keyword);
				}
				if(!condition.equals("username")) {
					postDto.setContent(keyword);
				}
			}
			/*
			 *  검색 키워드에는 한글이 포함될 가능성이 있기 때문에 링크에 그대로 출력 가능하도록 하기 위해 
			 *  미리 인코딩을 해서 request에 담아준다.
			 */
			String encodedKeyword = null;
			try {
				encodedKeyword = URLEncoder.encode(keyword,"utf-8");
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			
			request.setAttribute("keyword", keyword);
			request.setAttribute("encodedKeyword", encodedKeyword);
			request.setAttribute("condition", condition);
		}
		
		// Search 할때 유저 목록을 request에 usersList 라는 키값으로 담는다. 
		List<UsersDto> usersList = usersDao.getList(usersDto);
		request.setAttribute("usersList", usersList);

		// Search 할때 POST 목록을 request에 postDto 라는 키값으로 담는다. 
		List<PostDto> postList = postDao.getList(postDto);
		request.setAttribute("postDto", postDto);
		
		
	}

	@Override
	public void removePostInfo(int num, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePost(PostDto dto, HttpServletRequest request) {
				// content 작성된 string 가지고 오기
				String content = request.getParameter("content");
			
			
				//파일을 저장할 폴더의 절대 경로를 얻어온다.
				String realPath=request.getSession()
						.getServletContext().getRealPath("/upload");
				//MultipartFile 객체의 참조값 얻어오기
				//FileDto 에 담긴 MultipartFile 객체의 참조값을 얻어온다.
				MultipartFile mFile=dto.getFile();
				//원본 파일명
				String orgFileName=mFile.getOriginalFilename();
				//파일 사이즈
				long fileSize=mFile.getSize();
				//저장할 파일의 상세 경로
				String filePath=realPath+File.separator;
				//디렉토리를 만들 파일 객체 생성
				File file=new File(filePath);
				if(!file.exists()){//디렉토리가 존재하지 않는다면
					file.mkdir();//디렉토리를 만든다.
				}
				//파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
				String saveFileName=
						System.currentTimeMillis()+orgFileName;
				try{
					//upload 폴더에 파일을 저장한다.
					mFile.transferTo(new File(filePath+saveFileName));
				}catch(Exception e){
					e.printStackTrace();
				}
				
				//Dto 객체에 추가 정보를 담는다.
				String id=(String)request.getSession().getAttribute("id");
				int usercode = (int)request.getSession().getAttribute("usercode");
				dto.setId(id);
				dto.setUsercode(usercode);
				dto.setSaveFileName(saveFileName);
				dto.setFileSize(fileSize);
				dto.setContent(content);
				//Dao 객체를 이용해서 DB 에 저장하기
				postDao.insert(dto);				
		
	}

	@Override
	public void getPostData(ModelAndView mView, int num) {
		// TODO Auto-generated method stub
		
	}

}
