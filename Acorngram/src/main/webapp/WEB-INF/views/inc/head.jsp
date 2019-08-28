<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta property="og:title" content="${param.title }" />
	<!-- meta property="og:image" content="http://ia.media-imdb.com/images/rock.jpg" /-->
	<title>${param.title }</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/${param.css}">
