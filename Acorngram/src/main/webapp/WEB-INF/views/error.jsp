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
		var code = ${code};
		
		var errorPanel = document.querySelector('.error');
		var errorMsg = errorPanel.querySelector('.error__msg');
		var logArea =  errorPanel.querySelector('.error__log');
		
		var msg; var log;
		
		switch(code){
			case 404:
				msg = "404";
				log = 'not found';
			break;
			case 500:
				msg = "500";
				log = '서버 오류';
			break;
			case 418:
				msg = "418";
				log = '커피 맛있다~';
			break;
			default:
				msg = "모름";
				log = '알 수 없는 에러';
		}
		
		errorMsg.innerText = msg;
		logArea.innerText = log;
		
		document.querySelector('script').remove();
	</script>
	
</body>
</html>