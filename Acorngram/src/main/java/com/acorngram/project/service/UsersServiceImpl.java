package com.acorngram.project.service;

import java.io.File;
import java.util.HashMap;
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

import com.acorngram.project.dao.UsersDao;
import com.acorngram.project.dto.UsersDto;

@Repository
public class UsersServiceImpl implements UsersService{
	
	
	@Autowired
	private UsersDao dao;
	
	

	// 회원 정보 DB 저장 
	@Override
	public void addUser(UsersDto dto, ModelAndView mView) {
		
		// 암호화 후에 Dto에 객체 담고 DB에 저장 
		String rawPwd = dto.getPw();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		dto.setPw(encoder.encode(rawPwd));
		dao.insert(dto);
	}

	// 로그인 시 아이디 유무 체크 
	@Override
	public boolean validUser(UsersDto dto, ModelAndView mView, HttpSession session, HttpServletRequest request) {
		//아이디 비밀번호가 유효한지 여부
		boolean isValid = false;
		
		String pwdHash = dao.getPwdHash(dto.getId());
		
		//만일 아이디에 해당하는 비밀번호가 존재한다면
		if(pwdHash != null) {
			//비번 일치 여부를 얻어낸다
			
			isValid = BCrypt.checkpw(dto.getPw(), pwdHash);
		}
		
		if(isValid) {
			//로그인 처리를 한다
			session.setAttribute("id", dto.getId());
			dto = dao.simpleData(dto.getId());
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
		UsersDto usersDto = dao.getData(id);
		mView.addObject("user", usersDto);
		
	}

	// 아이디 중복 체크 
	@Override
	public Map<String, Object> isExistId(String inputId) {
		
		boolean isExist = dao.idCheck(inputId);
		Map<String,	Object> map = new HashMap<>();
		map.put("istExist", isExist);
		
		return map;
	}

	// 회원 정보 삭제 
	@Override
	public void deleteUser(HttpSession session) {
		String id = (String)session.getAttribute("id");
		dao.delete(id);
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
			dao.update(dto);
		
		//dto.getPw()의 값이 존재 한다면 우선 비번 수정임을 말함..
		}else {
			
			String pw = dto.getPw();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String savePwd = encoder.encode(pw);
			dto.setPw(savePwd);
			
			dao.updatePwd(dto);
		}
		
		
		
		
		
	}
	
}
