package com.acorngram.project.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dao.CommentDao;
import com.acorngram.project.dao.FollowerDao;
import com.acorngram.project.dao.LikesDao;
import com.acorngram.project.dao.PostDao;
import com.acorngram.project.dao.UsersDao;
import com.acorngram.project.dto.FollowerDto;
import com.acorngram.project.dto.LikedDto;
import com.acorngram.project.dto.PostDto;
import com.acorngram.project.dto.UsersDto;

@Repository
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private LikesDao likesDao;
	
	@Autowired
	private FollowerDao followerDao;
	
	

	// 회원 정보 DB 저장 
	@Override
	public void addUser(UsersDto dto, ModelAndView mView) {
		
		// 암호화 후에 Dto에 객체 담고 DB에 저장 
		String rawPwd = dto.getPw();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		dto.setPw(encoder.encode(rawPwd));
		usersDao.insert(dto);
	}

	// 로그인 시 아이디 유무 체크 
	@Override
	public boolean validUser(UsersDto dto, ModelAndView mView, HttpSession session, HttpServletRequest request) {
		//아이디 비밀번호가 유효한지 여부
		boolean isValid = false;
		
		String pwdHash = usersDao.getPwdHash(dto.getId());
		
		//만일 아이디에 해당하는 비밀번호가 존재한다면
		if(pwdHash != null) {
			//비번 일치 여부를 얻어낸다
			
			isValid = BCrypt.checkpw(dto.getPw(), pwdHash);
		}
		
		if(isValid) {
			//로그인 처리를 한다
			session.setAttribute("id", dto.getId());
			dto = usersDao.simpleData(dto.getId());
			session.setAttribute("usercode", dto.getUsercode());
			session.setAttribute("nickname", dto.getNickname());
			session.setAttribute("profile_img", dto.getProfile_img());
			return true;
		}else {
			return false;
		}
		
	}

	// 회원 정보 
	@Override
	public void showInfo(HttpSession session, ModelAndView mView) {
		String id = (String)session.getAttribute("id");
		UsersDto usersDto = usersDao.getData(id);
		mView.addObject("user", usersDto);
		
	}

	// 아이디 중복 체크 
	@Override
	public Map<String, Object> isExistId(String inputId) {
		
		boolean isExist = usersDao.idCheck(inputId);
		Map<String,	Object> map = new HashMap<>();
		map.put("istExist", isExist);
		
		return map;
	}

	// 회원 정보 삭제 
	@Override
	public void deleteUser(HttpSession session) {
		String id = (String)session.getAttribute("id");
		usersDao.delete(id);
		session.invalidate();
		
	}

	//  프로 파일 이미지를 저장하는 메서드 
	@Override
	public String saveProfileImage(HttpServletRequest request, MultipartFile mFile) {
		//파일 저장할 폴더의 절대 경로 가져오기
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		
		//원본 파일 명
		String orgFileName = mFile.getOriginalFilename();
		
		//저장할 파일의 상세 경로
		String filePath = realPath+File.separator;
		
		//디렉토리를 만들 파일 객체 생성
		File file = new File(filePath);
		
		if(!file.exists()) {
			file.mkdir();
		}
		//파일 시스템에 저장할 파일명을 마든다.
		String saveFileName = System.currentTimeMillis()+orgFileName;
		try {
			//upload folder에 파일 저장하기
			mFile.transferTo(new File(filePath+saveFileName));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String path = "/upload/" + saveFileName;
		//로그인 아이디
		String id = (String)request.getSession().getAttribute("id");
		
		//가져온 아이디로 이미지 경로를 담기 위해 dto생성하기
		UsersDto dto = new UsersDto();
		//아이디와 프로파일 이미지 경로 dto에 담고 보내기
		dto.setId(id);
		dto.setProfile_img(path);
		//UsersDao를 통해 DB에 src패스 반영하기
//		dao.updateProfileImg(dto);
		
		//이미지 경로 리턴해주기
		return path;
	}

	// 개인정보 수정 반영하는 메소드
	@Override
	public void updateUser(UsersDto dto, HttpServletRequest request) {
	
		// session 에서 id를 가지고 와서 dto.setId
		dto.setId((String) request.getSession().getAttribute("id"));
		
		// 비밀번호 변경인지, 다른 개인정보 수정인지 판별 
		// dto속에 pw 값이 없으면 비번 수정 작업이 요청이 아님
		if(dto.getPw() == null) {
		//이 update은 비번 외에 나머지 유저정보를 수정하는 메소드
			String dm_range = Optional.ofNullable(request.getParameter("dm_range")).orElse("0");
			String acc_private = Optional.ofNullable(request.getParameter("acc_private")).orElse("no");
			dto.setDm_range(Integer.parseInt(dm_range));
			dto.setAcc_private(acc_private);
			usersDao.update(dto);
		
		//dto.getPw()의 값이 존재 한다면 우선 비번 수정임을 말함..
		}else {
			
			String pw = dto.getPw();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String savePwd = encoder.encode(pw);
			dto.setPw(savePwd);
			
			usersDao.updatePwd(dto);
		}
	}
	
	// 프로필 영역에서 자신의 포스트 불러오기 
	@Override
	public void getProfileList(HttpServletRequest request) {
		
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
//		System.out.println("printing postDao.getCount result : " + totalRow);
		
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

		//거기에 해당하는 글 목록을 select해서 불러온다
		List<PostDto> postList = postDao.getList(postDto);
		
		//check if it received the list and will print list.size();
		System.out.println("list 사이즈 출력 : " + postList.size());
//		System.out.println("postList에 first index의 num가지고 오기 : " + postList.get(0).getNum());
		
		//likesDto instantiate
		LikedDto likeDto = new LikedDto();
		//IF each of the postlist index contains at least 1 like then it will call for info from likes table
		
		for(PostDto temp : postList) {
			likeDto.setPost_num(temp.getNum());
			likeDto.setUser_code((int)request.getSession().getAttribute("usercode"));
			int isLiked = likesDao.getLikedPost(likeDto);
			if(isLiked == 0) {
				temp.setLiked(false);
			}else {
				temp.setLiked(true);
			}
		}
		
		//get follow list for posts
		List<FollowerDto> followerList = followerDao.getList((int)request.getSession().getAttribute("usercode"));
		System.out.println("FOLLOWERLIST 존재여부 체크중... : "+followerList.size());
		for(FollowerDto temp : followerList) {
			if(temp.getTarget_usercode() == postDto.getUsercode() && temp.getStatus() == 1) {
				System.out.println("postDto의 usercode"+postDto.getUsercode());
				System.out.println("followerlist의 usercode"+temp.getTarget_usercode());
				System.out.println(temp.getStatus());
				postDto.setFollowed(true);
			}
			else {
				System.out.println("postDto의 usercode"+postDto.getUsercode());
				System.out.println("followerlist의 usercode"+temp.getTarget_usercode());
				System.out.println(temp.getStatus());
				postDto.setFollowed(false);
			}
		}
		
		//파일 목록을 request 에 list라는 키값으로 담는다
		request.setAttribute("list", postList);
		request.setAttribute("startRowNum", startRowNum);
		request.setAttribute("endRowNum", endRowNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("totalRow", totalRow);
		
	}
	
	
	
	
}
