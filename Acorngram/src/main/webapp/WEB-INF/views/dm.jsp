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
			<h3>준비중 입니다.</h3>
		</div>
	</main>
	
	<jsp:include page="inc/footer.jsp" />
</body>
</html>