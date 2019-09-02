
package com.acorngram.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acorngram.project.dto.CommentDto;
import com.acorngram.project.dto.FollowerDto;
import com.acorngram.project.dto.LikedDto;
import com.acorngram.project.dto.PostDto;
import com.acorngram.project.dto.UsersDto;
import com.acorngram.project.service.CommentsService;
import com.acorngram.project.service.FollowerService;
import com.acorngram.project.service.LikesService;
import com.acorngram.project.service.PostService;
import com.acorngram.project.service.UsersService;

@Controller
public class MainController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private PostService postService;

	@Autowired
	private FollowerService followerService;
	
	@Autowired private CommentsService commentsService;
	
	@Autowired private LikesService likesService;
	
	@RequestMapping(value="/users/signup.do", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute UsersDto dto, ModelAndView mView) {
		
		usersService.addUser(dto, mView);
		mView.setViewName("home");
		return mView;
	}
	
	@RequestMapping(value="/users/signin.do", method = RequestMethod.POST)
	public 	ModelAndView signIn(@ModelAttribute UsersDto dto, ModelAndView mView, HttpServletRequest request) {
		
		boolean isSuccessful = usersService.validUser(dto, mView, request.getSession(), request);
		System.out.println("isSuccessful : " + isSuccessful);
		//원래 가려던 url 정보를 reqeust 에 담는다.
//		String encodedUrl = URLEncoder.encode(request.getParameter("url"));
//		request.setAttribute("encodedUrl", encodedUrl);
		
		if(isSuccessful) {
			System.out.println("usercode in session? : "+request.getSession().getAttribute("usercode"));
			System.out.println("id in session? : "+request.getSession().getAttribute("id"));
			mView.setViewName("redirect:/home.do");
		}
		else {
			mView.setViewName("redirect:/home.do");
		}

		return mView;	
	}
	// /users/settings.do 와 같은 내용 
	@RequestMapping(value = "/users/profile.do", method = RequestMethod.POST)
	public ModelAndView authUpdateUserInfo(@ModelAttribute UsersDto dto, ModelAndView mView, HttpServletRequest request) {
		//유저 정보 수정 하는 메소드 호출
		usersService.updateUser(dto, request);
		mView.setViewName("users/profile");
		return mView;
	}
	
	//profile image upload 요청처리 부분
	@RequestMapping("/users/profile_upload")
	@ResponseBody
	public Map<String, Object> authProfileUpload(HttpServletRequest request, @RequestParam MultipartFile ProfileImage){
		//서비스를 이용해서 프로파일 이미지를 저장하고 저장된 이미지 경로를 리턴 받는다
		String path = usersService.saveProfileImage(request, ProfileImage);
		//JSON 문자열 응답하기
		Map<String, Object> map = new HashMap<>();
		map.put("path", path);
		return map;
	}
	
	@RequestMapping("/users/checkid.do")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId){
		Map<String, Object> map = usersService.isExistId(inputId);
		return map;
	}
	
	@RequestMapping("/users/delete.do")
	public ModelAndView authdelete(HttpServletRequest request) {
		
		usersService.deleteUser(request.getSession());
		return new ModelAndView("redirect:/home.do");
	}
	
	@RequestMapping("/users/signout.do")
	public String logout(HttpServletRequest request, ModelAndView mView) {
		request.getSession().invalidate();
		return "redirect:/home.do";
	}
	
	@RequestMapping("/users/settings.do")
	public ModelAndView authUsersSetting(HttpServletRequest request, ModelAndView mView) {
		usersService.showInfo(request.getSession(), mView);
		mView.setViewName("users/settings");
		return mView;
	}
	
	@RequestMapping("/users/updateSettings.do")
	public ModelAndView authUpdateSettings(@ModelAttribute UsersDto dto, @RequestParam MultipartFile ProfileImage, HttpServletRequest request) {
		
		//서비스를 이용해서 프로파일 이미지를 저장하고 저장된 이미지 경로를 리턴 받는다		
		if(ProfileImage.getSize()!=0) {
			String path = usersService.saveProfileImage(request, ProfileImage);
			dto.setProfile_img(path);
			request.getSession().setAttribute("profile_img", dto.getProfile_img());
		}
		
		usersService.updateUser(dto, request);
		
		return new ModelAndView("redirect:/users/settings.do");
	}
	
	
	
	//==============follow/unfollow 작업 요청 부분 ===============
	
	@RequestMapping(value = "/follower/follow.do", method = RequestMethod.POST)
	public ModelAndView authFollow(HttpServletRequest request,@RequestParam int target_userCode) {
		ModelAndView mView = new ModelAndView();
		followerService.follow(target_userCode, request, mView);
		//성공적으로 follow가 DB에 반영이 되었는지의 여부를 담아 JSON타입으로 mView에 담고 리턴하기
		return mView;
	}
	
	@RequestMapping(value = "/follower/unfollow.do", method = RequestMethod.POST)
	public ModelAndView authUnfollow(HttpServletRequest request, @RequestParam int target_userCode) {
		ModelAndView mView = new ModelAndView();
		followerService.unfollow(target_userCode, request, mView);
		//성공적으로 unfollow가 DB에 반영이 되었는지의 여부를 담아 JSON타입으로 mView에 담고 리턴하기
		return mView;
	}
	
	@RequestMapping("/follower/followerList.do")
	public ModelAndView authFollowerList(HttpServletRequest request, @RequestParam int self_userCode) {
		ModelAndView mView = new ModelAndView();
		List<FollowerDto> list = followerService.followerList(self_userCode);
		mView.addObject("followerList", list);
		return mView;
	}
	
	// ============= POST SECTION START =============
	
		// Upload content, img method
		@RequestMapping("/timeline.do")
		public ModelAndView postList(HttpServletRequest request) {
			postService.getList(request);
			return new ModelAndView("timeline");
		}
		
		//timeline.do 요청 했을때
		@RequestMapping("/post/write.do")
		public ModelAndView authPostUload(@ModelAttribute PostDto dto, HttpServletRequest request) {
			postService.savePost(dto, request);
			return new ModelAndView("redirect:/timeline.do");
		}
		// POST 삭제
		@RequestMapping("/post/delete.do")
		@ResponseBody
		public Map<String, Object> authDelete(HttpServletRequest request, HttpServletResponse response,
				@RequestParam int num) {
			Map<String, Object> map = new HashMap<>();
			if(postService.removePostInfo(num, request, response)) {
				map.put("result", true);
			}else {
				map.put("result", false);
			}
			return map;
		}
		
		//호진이가 다음에 AJAX로 사용할때 활용한대
//		@RequestMapping("/post/detail.do")
//		@ResponseBody
//		public Map<String, Object> authDetail(HttpServletRequest request, HttpServletResponse response,@RequestParam int num){
//			Map<String, Object> map = new HashMap<>();
//			postService.getPostData(map, num);
//			//map을 리턴해줌
//			return map;
//		}
		
//================================ POST DETAIL 영역 요청 처리 부분 ===========================================
		
		@RequestMapping("/post/detail.do")
		public ModelAndView authDetail(HttpServletRequest request, @RequestParam int num, ModelAndView mView) {
			postService.getPostData(num, request, mView);
			
			//check if mView added objects have crossed over
			System.out.println("controller에서의 commentList : " + request.getAttribute("commentList"));
			
			return mView;
		}
		
//================================COMMENTS 요청 처리 부분 ===================================================
		
		@RequestMapping("/comment/write.do")
		public ModelAndView authWrite(HttpServletRequest request, @ModelAttribute CommentDto commentDto) {
			commentsService.writeComment(request, commentDto);
			return new ModelAndView("redirect:/timeline.do");
		}
		
//===============================LIKING POSTS section ===================================================
		
		@RequestMapping("/post/like.do")
		@ResponseBody
		public Map<String, Object> authLike(HttpServletRequest request, @RequestParam int num) {
			Map<String , Object> map = new HashMap<>();
			LikedDto likedDto = new LikedDto();
			likedDto.setPost_num(num);
			likesService.likePost(likedDto, request);
			map.put("result", true);
			return map;
		}
		
		@RequestMapping("/post/unlike.do")
		@ResponseBody
		public Map<String, Object> authUnlike(HttpServletRequest request, @RequestParam int num) {
			Map<String , Object> map = new HashMap<>();
			LikedDto likedDto = new LikedDto();
			likedDto.setPost_num(num);
			likesService.unlikePost(likedDto, request);
			map.put("result", true);
			return map;
		}
	
}//UsersController END

