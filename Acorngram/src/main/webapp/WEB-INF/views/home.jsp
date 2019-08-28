<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="inc/head.jsp">
		<jsp:param value="메인페이지" name="title"/>
	</jsp:include>
	<style>
		label{display:block}
		input+label{display:inline-block}
		
	</style>
</head>
<body>
	<div class="container">
		<c:choose>
			
			<%-- 로그인 상태일 때 --%>
			<%-- 실제로는 timeline이 표시됨 --%>
			<c:when test="${not empty id }">
				<h2>로그인 성공</h2>
				<span>로그인 계정: <strong>${id }</strong></span>
				<a href="users/signout.do">
					로그아웃하기
				</a>
			</c:when>
			
			<%-- 비로그인 상태일 때 --%>
			<c:otherwise>
				<article>
					<h1>로그인 폼</h1>
					<form action="users/signin.do" method="post" id="signin">
						<label for="">
							아이디
							<input type="text" name="id" id="signin-id" />
						</label>
						<label for="">
							패스워드
							<input type="password" name="pw" id="signin-pw" />
						</label>
						<button>확인</button>
					</form>
				</article>
				<article>
					<h1>회원가입 폼</h1>
					<form action="" method="post" id="signUp">
						<label for="">
							아이디
							<input type="text" name="id" id="signup-id" />
						</label>
						<label for="">
							닉네임
							<input type="text" name="nickname" id="signup-nickname" />
						</label>
						<label for="">
							패스워드
							<input type="password" name="pw" id="signup-pw" />
						</label>
						<label for="">
							패스워드 확인
							<input type="password" name="pw-c" id="signup-pw-c" />
						</label>
						<label for="">
							이메일
							<input type="email" name="email" id="signup-email" />
						</label>
						<p>계약에 동의하고 어쩌구 저쩌구</p>
						<input type="checkbox" name="agree" id="signup-agree" />
						<label for="signup-agree">동의</label>
						<button>확인</button>
					</form>
				</article>
			</c:otherwise>
			
		</c:choose>
	</div>
</body>
</html>