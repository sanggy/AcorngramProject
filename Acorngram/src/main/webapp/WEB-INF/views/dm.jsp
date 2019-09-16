<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="inc/head.jsp">
		<jsp:param value="DM" name="title"/>
		<jsp:param value="dm" name="css"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="inc/header.jsp" />
	
	<main>
		<div class="container">
			<nav class="inner-header">
				<a href="${pageContext.request.contextPath}/timeline.do"><i class="glyphicon glyphicon-chevron-left"></i> 돌아가기</a>
			</nav>
		</div>
		<article class="dm__panel container">
			<div class="dm__header">
				<img src="${pageContext.request.contextPath}/${post.profile_img }" alt="" class="post__icon"/>
				<hgroup>
					<h5 class="post__name"> ${post.nickname } </h5>
					<h6 class="post__id"> @${post.id } </h6>
				</hgroup>
			</div>
			<div class="dm__msg-list">
				
			</div>
			<div class="dm__footer">
				<textarea name="msg" id="dm-msg" maxlength="200"></textarea>
				<button type="button">전송</button>
			</div>
		</article>
	</main>
	
	<jsp:include page="inc/footer.jsp" />
</body>
</html>