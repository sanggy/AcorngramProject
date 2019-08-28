<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="header">
	<div class="container">
		<div class="header__logo">
			<h1 id="logo"><a href="${pageContext.request.contextPath}/home.do">AcornGram</a></h1>
		</div>
		<c:choose>
			<c:when test="${empty id }">
				<div class="header__signin">
					<input type="checkbox" class="toggler" id="signinform-toggler">
					<label class="toggler" for="signinform-toggler">로그인</label>
					<section class="header__signin-form">
						<form action="users/signin.do" method="post" id="signUp" class="signin__form">
							<div class="form-group">
								<label class="signin__label">아이디</label>
								<input type="text" name="id" id="signup-id" class="form-control form-control-sm"/>
							</div>
							<div class="form-group">
								<label class="signin__label">패스워드</label>
								<input type="password" name="pw" id="signup-pw"  class="form-control form-control-sm"/>
							</div>
							<button class="signin__button">로그인</button>
						</form>
					</section>
				</div>
			</c:when>
			<c:otherwise>
				<div class="header__user-info">
					<input type="checkbox" class="toggler" id="usermenu-toggler">
					<label class="toggler" for="usermenu-toggler">
						<img src="" alt="" />
						<span>@${id }</span>
					</label>
					<section class="header__user-menu">
						<ul>
							<li><a href="users/profile.do?usercode=${usercode }">프로필</a></li>
							<li><a href="users/setting.do">설정</a></li>
							<li><a href="users/signout.do">로그아웃</a></li>
						</ul>
					</section>
				</div>
				<div class="header__writepost">
					
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</header>