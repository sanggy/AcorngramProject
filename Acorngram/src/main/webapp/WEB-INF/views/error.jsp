<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="inc/head.jsp">
		<jsp:param value="ERROR" name="title"/>
		<jsp:param value="error" name="css" />
	</jsp:include>
</head>
<body>
	
	<%-- 이스터에그: 418 : I'm not teapot --%>
	<c:choose>
		<c:when test="${code eq 418 }">
			<c:set var="img" value="tpot.png" />
		</c:when>
		<c:otherwise>
			<c:set var="img" value="error.jpg" />
		</c:otherwise>
	</c:choose>
	
	<main>
		<article class="error container">
			<div class="error__img" style="background-image: url('${pageContext.request.contextPath}/resources/images/${img }')">
			</div>
			<div class="error__info">
				<h2>Oops...</h2>
				<hr />
				<p class="error__msg"></p>
				<p>아래 메시지를 운영자에게 전송하면 도움이 될지도 모르겠군요...</p>
				<code class="error__log"></code>
				<a href="${pageContext.request.contextPath}/" role="button">메인 페이지로</a>
			</div>
		</article>
	</main>
	
	<script>
		const code = ${code};
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/errorPage.js"></script>
	
</body>
</html>