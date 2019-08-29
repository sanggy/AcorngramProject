<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- request.getSession().setAttribute("id", "test"); --%>
<c:if test="${not empty usercode}">   
	<c:redirect url="timeline.do"/> 
</c:if>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="inc/head.jsp">
		<jsp:param value="메인페이지" name="title"/>
		<jsp:param value="home" name="css" />
	</jsp:include>
</head>
<body>
	<jsp:include page="inc/header.jsp" />
	
	<main>
		<div class="container">
			<section class="introduce">
				<div class="introduce__description">
					<h2>즐거운 세상을 공유하세요!</h2>
					<p>소개글이 들어가는 공간으로, Lorem ipsum dolor sit amet consectetur adipisicing elit. Dicta laboriosam vel est, laudantium unde accusamus, ad fugit cum architecto iure aperiam rerum eum a sapiente dolorem excepturi, culpa ut! Ab!</p>
				</div>
			</section>
			<section class="signup">
				<form action="users/signup.do" method="post" id="signUp" class="signup__form">
					<fieldset>
						<legend>회원등록은 간단합니다!</legend>
						<div class="form-group">
							<label class="signup__label"> 아이디 </label>
							<input type="text" name="id" id="signup-id" class="form-control form-control-sm"/>
						</div>
						<div class="form-group">
							<label class="signup__label"> 닉네임 </label>
							<input type="text" name="nickname" id="signup-nickname" class="form-control form-control-sm" />
						</div>
						<div class="form-group">
							<label class="signup__label"> 패스워드 </label>
							<input type="password" name="pw" id="signup-pw"  class="form-control form-control-sm" placeholder="영대문자 1자 이상인 최소 8자이상의 영숫자" />
						</div>
						<div class="form-group">
							<label class="signup__label"> 패스워드 확인 </label>
							<input type="password" name="pw-c" id="signup-pw-c"  class="form-control form-control-sm"/>
						</div>
						<div class="form-group">
							<label class="signup__label"> 이메일 </label>
							<input type="email" name="email" id="signup-email" class="form-control form-control-sm" />
						</div>
						<div class="form-group">
							<input type="checkbox" name="agree" id="signup-agree" class="form-check-input" />
							<label for="signup-agree" class="form-check-label"><a href="">회원규약</a>을 읽었으며 회원가입에 동의합니다.</label>
						</div>
						<button class="signup__button"><span class="glyphicon glyphicon-saved"></span> 회원등록</button>
					</fieldset>
				</form>
			</section>
		</div>
	</main>
	<jsp:include page="inc/footer.jsp" />
</body>
</html>