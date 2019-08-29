<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="inc/head.jsp">
		<jsp:param value="검색" name="title"/>
		<jsp:param value="search" name="css"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="inc/header.jsp" />
	<main>
		<div class="container">
			<c:if test="${not empty list }">
			<c:forEach var="post" items="list">
				<li>num: ${post.num }</li>
				<li>usercode: ${post.usercode }</li>
				<li>username: ${post.name }</li>				
				<li>@${post.id }</li>
				<li><img src="${post.image }" alt="" width="300"/></li>
				<li>content: ${post.content }</li>
				<li>like: ${post.like_count }</li>		
				<li>regdate: ${post.regdate }</li>		
			</c:forEach>
		</c:if>
		</div>
	</main>
	<jsp:include page="inc/footer.jsp" />
</body>
</html>