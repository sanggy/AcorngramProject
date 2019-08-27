package com.gura.spring08.aspect;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class LoginAspect {
	
	//Map형을 리턴하는 메소드를 위한 AOP
	@Around("execution(java.util.Map auth*(..))") //return type은 ModelAndView... (package명까지 다 정확히 써야함)
	public Object loginCheckAjax(ProceedingJoinPoint joinPoint) throws Throwable {
		//aop가 적용된 메소드에 전달된 값을 Object[] 로 얻어오기 --> AOP가 적용된 method's parameters can be of any datatype thus to check for servletrequest
														//datatype, we first take all parameters into Object[] and run through instanceof
		Object[] args = joinPoint.getArgs();
		//로그인 여부
		boolean isLogin = false;
		HttpServletRequest request = null; //request가 꼭 필요함 IF you want to check if user has logged in first..
		for(Object tmp:args) {
			//인자롤 전달된 값중에 HttpServletRequest type을 찾아서
			if(tmp instanceof HttpServletRequest) {
				//원래 type으로 casting
				request = (HttpServletRequest)tmp;
				//HttpSession 겍체 얻어내기
				HttpSession session = request.getSession();
				//세션에 "id"라는 키값으로 저장된게 있는지 확인(로그인 여부)
				if(session.getAttribute("id") != null) {
					isLogin = true;
				}
			}
		}
		//로그인 헀는지 여부
		if(isLogin) {
			//aop 가 적용된 메소들을 실행하고
			Object obj = joinPoint.proceed();
			//리턴되는 값을 리턴해주기
			return obj;
		}
		//로그인 하지 않았으면
		Map<String, Object> map = new HashMap<>();
		map.put("isSuccess", false);
		return map;
	}
	
	
	
	/*
	 * auth로 시작하는 모든 메소드에 적용되는 AOP.. 그리고 인자는 아무거나 다 가능함. 있어도/없어도/무엇이든
	*/
	@Around("execution(org.springframework.web.servlet.ModelAndView auth*(..))") //return type은 ModelAndView... (package명까지 다 정확히 써야함)
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		//aop가 적용된 메소드에 전달된 값을 Object[] 로 얻어오기 --> AOP가 적용된 method's parameters can be of any datatype thus to check for servletrequest
														//datatype, we first take all parameters into Object[] and run through instanceof
		Object[] args = joinPoint.getArgs();
		//로그인 여부
		boolean isLogin = false;
		HttpServletRequest request = null; //request가 꼭 필요함 IF you want to check if user has logged in first..
		for(Object tmp:args) {
			//인자롤 전달된 값중에 HttpServletRequest type을 찾아서
			if(tmp instanceof HttpServletRequest) {
				//원래 type으로 casting
				request = (HttpServletRequest)tmp;
				//HttpSession 겍체 얻어내기
				HttpSession session = request.getSession();
				//세션에 "id"라는 키값으로 저장된게 있는지 확인(로그인 여부)
				if(session.getAttribute("id") != null) {
					isLogin = true;
				}
			}
		}
		//로그인 헀는지 여부
		if(isLogin) {
			//aop 가 적용된 메소들을 실행하고
			Object obj = joinPoint.proceed();
			//리턴되는 값을 리턴해주기
			return obj;
		}
		//원래 가려던 url 정보 읽어오기
		String url = request.getRequestURI();
		//GET 방식 전송 파라미터를 query string 으로 읽어괴
		String query = request.getQueryString();
		
		String encodedUrl = null;
		if(query == null) {//전달된 파라미터가 없다면
			encodedUrl = URLEncoder.encode(url);
		}else {
			encodedUrl = URLEncoder.encode(url+"?"+query);
		}
		//ModelAndView 겍체를 생성해서
		ModelAndView mView = new ModelAndView();
		//로그인 폼으로 리다일랙트 시키도록 view page 설정
		mView.setViewName("redirect:/users/loginform.do?url=" + encodedUrl);
		
		//여기서 생성한 객체를 리턴해 준다.
		return mView;
	}
}
