
package com.acorngram.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.annotation.SendToUser;
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
import com.acorngram.project.service.DirectMessageService;
import com.acorngram.project.service.FollowerService;
import com.acorngram.project.service.LikesService;
import com.acorngram.project.service.PostService;
import com.acorngram.project.service.UsersService;
//import com.google.gson.Gson;

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
	
	//websocket simple version of message sending operation to use STOMP
//	@Autowired private SimpMessageSendingOperations messagingTemp;
	
	
	@Autowired private DirectMessageService directMessageService;
	
	//websocket simple version of message sending operation to use STOMP
//	@Autowired private SimpMessageSendingOperations messagingTemp;
	
		
	
	@RequestMapping(value="/users/signup.do", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute UsersDto dto, ModelAndView mView) {
		
		usersService.addUser(dto, mView);
		mView.setViewName("home");
		return mView;
	}
	
	@RequestMapping(value="/users/signin.do", method = RequestMethod.POST)
	public 	ModelAndView signIn(@ModelAttribute UsersDto dto, ModelAndView mView, HttpServletRequest request) {
		
		boolean isSuccessful = usersService.validUser(dto, mView, request.getSession(), request);

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
		usersService.showInfo((String)request.getSession().getAttribute("id"), mView, request);
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
	
	// Password button click to forward page 
	@RequestMapping("/users/settings_pwd.do")
	public ModelAndView authSettingsPwd(HttpServletRequest request) {
		return new ModelAndView("users/settings_pwd");
	}
	
	@RequestMapping("/users/change_pwd.do")
	public ModelAndView authChangePwd(ModelAndView mView, HttpServletRequest request, @ModelAttribute UsersDto dto) {
		usersService.updateUser(dto, request);		
		return new ModelAndView("redirect:/users/settings.do");
	}
	
	@RequestMapping("/users/profile.do")
	public ModelAndView authProfile(ModelAndView mView, HttpServletRequest request, @RequestParam String id) {
		usersService.showInfo(id, mView, request);
		mView.setViewName("users/profile");
		return mView;
	}
	
	//==============follow/unfollow 작업 요청 부분 ===============
	
	@RequestMapping("/follower/follow.do")
	@ResponseBody
	public Map<String, Object> authFollow(HttpServletRequest request,@RequestParam int usercode) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isFollowed = followerService.follow(usercode, request);
		//성공적으로 follow가 DB에 반영이 되었는지의 여부를 담아 JSON타입으로 mView에 담고 리턴하기
		map.put("result", isFollowed);
		return map;
	}
	
	@RequestMapping("/follower/unfollow.do")
	@ResponseBody
	public Map<String, Object> authUnfollow(HttpServletRequest request, @RequestParam int usercode) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isUnfollowed = followerService.unfollow(usercode, request);
		//성공적으로 unfollow가 DB에 반영이 되었는지의 여부를 담아 JSON타입으로 mView에 담고 리턴하기
		map.put("result", isUnfollowed);
		return map;
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
			return new ModelAndView("redirect:/post/detail.do?num="+commentDto.getRef_group());
		}
		
		@RequestMapping("/comment/delete.do")
		public String authDelete(HttpServletRequest request, @RequestParam int num, @RequestParam int post_num) {
			System.out.println(num +"&"+ post_num + "여기 maincontrol영역이야");
			commentsService.deleteComment(num);
			return "redirect:/post/detail.do?num="+post_num;
			
		}
		
		
//===============================LIKING POSTS section ===================================================
		
		@RequestMapping("/post/like.do")
		@ResponseBody
		public Map<String, Object> authLike(HttpServletRequest request, @RequestParam int num, ModelAndView mView) {
			Map<String , Object> map = new HashMap<>();
			LikedDto likedDto = new LikedDto();
			likedDto.setPost_num(num);
			likesService.likePost(likedDto, request);
			postService.increaseLikeCount(num);
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
			postService.decreaseLikeCount(num);
			map.put("result", true);
			return map;
		}
		
//============================= DM CONTROLLER =============================================================
//		@MessageMapping("/users/dm")
//		@SendToUser("/queue/directMessage")
//		public String processMessageFromClient(@Payload String message, Principal principal) throws Exception {
//			
//			
////			this.messagingTemp.convertAndSendToUser(message.getFrom(), "/queue/directMessage", message.getMessage());
//			
//			return gson.fromJson(message, Map.class).get("name").toString();
//		}
//		
//		@MessageExceptionHandler
//		@SendToUser("/gueue/errors")
//		public String handleException(Throwable exception) {
//			return exception.getMessage();
//		}
//		
		@RequestMapping("/users/dm.do")
		public ModelAndView directMessage(HttpServletRequest req, ModelAndView mView) {
			return new ModelAndView("users/dm");
		}
		
		
//=============================== TEST section ===================================================
		
		//	Error 페이지 호출
		@RequestMapping("/error.do")
		public ModelAndView showError(HttpServletRequest req) {
			ModelAndView mv = new ModelAndView("error");
			String code = Optional.ofNullable(req.getParameter("code")).orElse("418");
			mv.addObject("code", code);
			return mv;
		}
		
//		//	DM 페이지 호출
//		@RequestMapping("/dm.do")
//		public ModelAndView enterDM(HttpServletRequest req) {
//			ModelAndView mv = new ModelAndView("dm");
//			String id = Optional.ofNullable(req.getParameter("id")).orElse("gura");
//			mv.addObject("target-id", id);
//			return mv;
//		}
		// POST 삭제
		
		//호진이가 다음에 AJAX로 사용할때 활용한대
//		@RequestMapping("/post/detail.do")
//		@ResponseBody
//		public Map<String, Object> authDetail(HttpServletRequest request, HttpServletResponse response,@RequestParam int num){
//			Map<String, Object> map = new HashMap<>();
//			postService.getPostData(map, num);
//			//map을 리턴해줌
//			return map;
//		}
		
		
		
		
//============================= DM CONTROLLER =============================================================
		@RequestMapping("/users/dm.do")
		public ModelAndView directMessage(HttpServletRequest req, ModelAndView mView, @RequestParam int num) {
			directMessageService.userProfile(num, req, mView);
			mView.addObject("targetUserCode", num);
			mView.setViewName("dm");
			return mView;
		}
		
		
		
//============================== SEARCH KEYWORD/CONDITION ================
		@RequestMapping("/search.do")
		public ModelAndView searchTimeline(HttpServletRequest request, ModelAndView mView) {
			postService.getList(request);
			mView.setViewName("timeline");
			return mView;
		}
	
}//UsersController END

