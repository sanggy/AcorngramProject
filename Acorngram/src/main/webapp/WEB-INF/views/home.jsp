<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="inc/head.jsp">
		<jsp:param value="메인페이지" name="title"/>
		<jsp:param value="home.css" name="css" />
	</jsp:include>
	<style>
		label{display:block}
		input+label{display:inline-block}
	</style>
</head>
<body>
	<jsp:include page="inc/header.jsp" />
	
	<main>
		<div class="container-fluid">
			<section class="introduce">
				<h1>즐거운 세상을 공유하세요!</h1>
				<p>소개글이 들어가는 공간으로, Lorem ipsum dolor sit amet consectetur adipisicing elit. Dicta laboriosam vel est, laudantium unde accusamus, ad fugit cum architecto iure aperiam rerum eum a sapiente dolorem excepturi, culpa ut! Ab!</p>
			</section>
			<section class="signup">
				<form action="users/signup.do" method="post" id="signUp">
					<label class="signup__label">
						아이디
						<input type="text" name="id" id="signup-id" />
					</label>
					<label class="signup__label">
						닉네임
						<input type="text" name="nickname" id="signup-nickname" />
					</label>
					<label class="signup__label">
						패스워드
						<input type="password" name="pw" id="signup-pw" />
					</label>
					<label class="signup__label">
						패스워드 확인
						<input type="password" name="pw-c" id="signup-pw-c" />
					</label>
					<label class="signup__label">
						이메일
						<input type="email" name="email" id="signup-email" />
					</label>
					<input type="checkbox" name="agree" id="signup-agree" />
					<label for="signup-agree"><a href="">회원규약</a>을 읽었으며 회원가입에 동의합니다.</label>
					<button>확인</button>
				</form>
			</section>
		</div>
	</main>
	<jsp:include page="inc/footer.jsp" />
</body>
</html>