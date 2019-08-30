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
		System.out.println("여기 들어왔나> 지금 getList service부분이다.");
		
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
		
		//paging 처리
		String id = (String) request.getSession().getAttribute("id");
		//한 페이지에 나타낼 row 의 갯수
		final int PAGE_ROW_COUNT = 5;
		//하단 디스플레이 페이지 갯수
		final int PAGE_DISPLAY_COUNT = 1;
		
		//보여줄 페이지의 번호
		int pageNum=1;
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum = request.getParameter("pageNum");
		//페이지 번호가 파라미터로 넘어온다면
		if(strPageNum != null) {
			//페이지 번호를 설정한다.
			pageNum = Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum = 1+(pageNum -1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum = pageNum*PAGE_ROW_COUNT;
		
		//전체 row의 갯수를 읽어온다.
		int totalRow = postDao.getCount(postDto);
		
		//trying to check if postDao.getCount will work ----- checked and it works
		System.out.println("printing postDao.getCount result : " + totalRow);
		
		//전체 페이지의 갯수 구하기
		int totalPageCount = (int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//시작 페이지 번호
		int startPageNum = 1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면
		if(totalPageCount < endPageNum) {
			endPageNum = totalPageCount; //보정해준다
		}
		//startRowNum 과 endRowNum 을 BoardDto 객체에 담고
//				FileDto dto = new FileDto();
		postDto.setStartRowNum(startRowNum);
		postDto.setEndRowNum(endRowNum);
		
		//checking if getList is gonna work...
		System.out.println("여기 들어온다... getList 실행할거야.");
		
		//거기에 해당하는 글 목록을 select해서 불러온다
		List<PostDto> postList = postDao.getList(postDto);
		
		//check if it received the list and will print list.size();
		System.out.println("list 사이즈 출력 : " + postList.size());
		System.out.println("postList에 first index의 num가지고 오기 : " + postList.get(0).getNum());
		
		//파일 목록을 request 에 list라는 키값으로 담는다
		request.setAttribute("list", postList);
		request.setAttribute("startRowNum", startRowNum);
		request.setAttribute("endRowNum", endRowNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("totalRow", totalRow);
		
		//checking if usersDao.getList is gonna work... 
		System.out.println("usersDao.getList를 실행 하럭야");
		
		// Search 할때 유저 목록을 request에 usersList 라는 키값으로 담는다. 
//		List<UsersDto> usersList = usersDao.getList(usersDto);
//		request.setAttribute("usersList", usersList);

		// Search 할때 POST 목록을 request에 postDto 라는 키값으로 담는다. 
		
		
		
	}

	@Override
	public void removePostInfo(int num, HttpServletRequest request, HttpServletResponse response) {
		postDao.delete(num);
	}

	@Override
	public void savePost(PostDto dto, HttpServletRequest request) {
				// content 작성된 string 가지고 오기
				String content = request.getParameter("content");
				//request로 num값 가지고 오기
//				int num = Integer.parseInt(request.getParameter("num"));
				
				//checking if num exists
//				System.out.println("num 값이 담겨서 넘어왔나요? :" + num);
			
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
				dto.setImage(filePath+saveFileName);
				dto.setContent(content);
				
				System.out.println("location of image file : " + filePath);
				System.out.println("orgFileName of file : " + orgFileName);
				
				//Dao 객체를 이용해서 DB 에 저장하기
				postDao.insert(dto);			
		
	}

	@Override
	public void getPostData(ModelAndView mView, int num) {
		// TODO Auto-generated method stub
		
	}

}