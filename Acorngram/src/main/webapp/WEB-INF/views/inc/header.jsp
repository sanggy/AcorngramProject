<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="header">
	<div class="container">
		<div class="header__logo">
			<h1 id="logo"><a href="/home.do">AcornGram</a></h1>
		</div>
		<div class="header__signin">
			<input type="checkbox" id="signinform-toggler">
			<label for="signinform-toggler">로그인</label>
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
	</div>
</header>