<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="header">
	<div class="container">
		<c:if test="${not empty usercode }">
			<%-- 메뉴 자리 --%>
		</c:if>
		
		<div class="header__logo">
			<h1 id="logo"><a href="${pageContext.request.contextPath}/home.do">AcornGram</a></h1>
		</div>
		
		<div class="header__right">
			
			<c:choose>
				<c:when test="${empty usercode }">
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
						<button type="button">
							<img src="${pageContext.request.contextPath}/${profile_img }" alt="" width="48" height="48"/>
						</button>
					</div>
					<div class="header__writepost">
						<button type="button">
							<i class="glyphicon glyphicon-edit"></i>
							<span>작성</span> 
						</button>
					</div>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
</header>

<c:if test="${not empty usercode }">
	<nav class="user-menu">
		<ul>
			<li class="user-menu__mini-profile">
				<strong class="user-menu__user-name"><c:out value="${name }" default="test" /></strong>
				<span class="user-menu__user-id">@${id }</span>
			</li>
			<li class="user-menu__line"></li>
			<li><i class="glyphicon--is-themed glyphicon-user"></i><a href="users/profile.do?usercode=${usercode }">프로필</a></li>
			<li><i class="glyphicon--is-themed glyphicon-wrench"></i><a href="users/setting.do">설정</a></li>
			<li><i class="glyphicon--is-themed glyphicon-log-out"></i><a href="users/signout.do">로그아웃</a></li>
		</ul>
	</nav>
	
	<div class="writepost container">
		<form action="post/write.do" id="writepost-form" class="writepost__form" enctype="multipart/form-data" method="post">
			<fieldset>
				<legend>
					<span>지금 내 앞엔?</span>
					<button>
						<i class="glyphicon glyphicon-check"></i> 
						<span>전송</span>	
					</button>
				</legend>
				<div class="writepost__body">
					<label for="writepost-img">
						<input type="file" name="file" id="writepost-img" accept="image/*" required/>
						<div class="writepost__image-info">
							<i class="glyphicon glyphicon-picture"></i>
							<p>클릭해서 사진 파일 업로드</p>
						</div>
						<img id="preview" width="360">
					</label>
					<textarea name="content" class="writepost__content" placeholder="내용" required></textarea>
				</div>
			</fieldset>
		</form>
	</div>
</c:if>