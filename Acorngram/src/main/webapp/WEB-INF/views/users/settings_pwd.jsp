<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../inc/head.jsp">
		<jsp:param value="Change Password" name="title"/>
		<jsp:param value="settings" name="css"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="../inc/header.jsp" />
	
	<main>
		<div class="user-settings container">
			<form class="user-settings__form" method="post" action="change_pwd.do">
				<fieldset class="user-settings__body">
					<legend>비밀번호 변경</legend>
					<div class="form-group">
						<label for="pw" class="ontrol-label">PASSWORD</label>
						<input type="password" class="form-control" name="pw" placeholder="영대소문자 및 숫자가 표함된 8자 이상">
					</div>
					<div class="form-group">
						<label for="pw-c" class="ontrol-label">PASSWORD CONFIRM</label>
						<input type="password" class="form-control" name="pw_c" placeholder="변경할 패스워드를 한번 더 적어주세요">
					</div>
					<div class="form-group">
						<button type="submit" class="btn-primary">변경</button>
						<a class="btn-danger" href="settings.do" role="button">취소</a>
					</div>
				</fieldset>
			</form>
		</div>
	</main>
	
	<jsp:include page="../inc/footer.jsp" />
</body>
</html>