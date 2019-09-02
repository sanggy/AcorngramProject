<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../inc/head.jsp">
		<jsp:param value="Settings" name="title"/>
		<jsp:param value="settings" name="css"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="../inc/header.jsp" />

	<main>
		<div class="user-settings container">

			<form class="user-settings__form" enctype="multipart/form-data" method="post" action="updateSettings.do">
				<div class="user-settings__header">
					<label for="profile-img">
						<input type="file" name="profile_file" id="profile-img" accept="image/*" />
						<img id="user__profile-img" class="user__profile-img"
						<%-- src="${pageContext.request.contextPath}/${user.profile_img}"--%>
						src="${pageContext.request.contextPath}/${user.profile_img}"
						alt="${user.id }의 프로필 이미지" />
					</label>
					<hgroup>
						<h5 class="user__name"> ${user.nickname } </h5>
						<h6 class="user__id"> @${user.id } </h6>
					</hgroup>
				</div>

				<fieldset class="user-settings__body">
					<legend>유저 설정</legend>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Nickname</label>
						<input type="text" class="form-control" name="nickname" value=${user.nickname }>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<input type="email" class="form-control" name="email" value="${user.email }">
					</div>

					<div class="form-group">
						<a href="settings_pwd.do" role="button" class="user__btn-change-pwd">패스워드 변경</a>
					</div>

					<div class="form-group">  
						<label for="inputEmail3" class="col-sm-2 control-label">Date of birth</label>
						<input type="date" name="dob" class="form-control" id="DOB" value="${user.dob}">
					</div>
					<div class="form-group toggler-wrap">
						<div class="user-settings__toggler">
							<input type="checkbox" id="dm_range" name="dm_range" class="switch-input" value="1">
							<label for="dm_range" class="switch-label">
								DM RANGE: 
								<span class="toggle--on">private</span>
								<span class="toggle--off">public</span>
							</label>
						</div>
						<div class="user-settings__toggler">
							<input type="checkbox" id="account_private" name="acc_private" class="switch-input" value="yes">
							<label for="account_private" class="switch-label">
								ACCOUNT is : 
								<span class="toggle--on">private</span>
								<span class="toggle--off">public</span>
							</label>
						</div>
						<div class="user-settings__toggler">
							<span>THEME</span>
							<select name="theme" id="user-setting__theme" class="switch-label">
								<option value="defl">기본값</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn-primary">Confirm</button>
						<a class="btn-danger" href="javascript:corfirmAccess('delete_account')" role="button">회원 탈퇴</a>
					</div>

				</fieldset>
			</form>

			</div>

	</main>

	<jsp:include page="../inc/footer.jsp" />
</body>
</html>