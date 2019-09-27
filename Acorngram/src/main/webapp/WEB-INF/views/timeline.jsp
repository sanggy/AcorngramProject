<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="inc/head.jsp">
		<jsp:param value="타임라인" name="title"/>
		<jsp:param value="timeline" name="css"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="inc/header.jsp" />
	<main>
		<div class="container">
			<div class="inner-header search">
				<form action="${pageContext.request.contextPath}/search.do" method="get" class="search__form">
					<div class="search__condition-selector">
						<select name="condition" id="search-condition">
							<c:set var="option" value="${['none','user','post']}"></c:set>
							<c:forEach var="item" items="${option}">
								<option value="${item }" <c:if test="${item eq param.condition}">selected</c:if> >
									<c:choose>
										<c:when test="${item eq 'none'}">ALL</c:when>
										<c:otherwise>${item}</c:otherwise>
									</c:choose>
								</option>
							</c:forEach>
						</select>
					</div>
					<input type="text" name="keyword" id="search-word" placeholder="검색어를 입력하세요" value="<c:out value='${ param.keyword}' default=''></c:out>"/>
					<button class="search__btn">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</form>
			</div>
			<c:if test="${not empty param.condition }">
				<nav class="inner-header">
					<a href="${pageContext.request.contextPath}/timeline.do"><i class="glyphicon glyphicon-chevron-left"></i>돌아가기</a>
				</nav>
			</c:if>
		</div>
		<div class="timeline container">
		<c:choose>
			<c:when test="${not empty list }">
				<jsp:include page="post/loadtl.jsp"/>
			</c:when>
			<c:otherwise>
				<p>검색결과가 존재하지 않습니다.</p>
			</c:otherwise>
		</c:choose>
		</div>
	</main>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.2.0/socket.io.js"></script>
	<script>
		//const socket = io(location.hostname+':3000');
		//const socket = io('http://211.106.163.151:3000');
		const socket = io('http://192.168.0.93:3000');
		
		//need to save socket in session...
	
		socket.on("connect", function(event){
			console.log("socket 연결되었습니다.");
			socket.emit("userConnected", {userId: '${id}'});
			//상대 유저아이디로 만들어진 방에 접속하기
			//socket.emit("/privateMsg/", {userId: '${id}', targetUserId: '${targetUser.id}'});
			//console.log("targetUserId" + '${targetUser.id}');
		});
		
		socket.on("disconnect", function(event){
			console.log("socket disconnected");
		});
		
		socket.on("/privateMsg/", function(event){
			//String target = event.targetUserId;
			if(event.targetUser == '${id}'){
				if(confirm("Do you wanna accept chat invitation from " + event.sender + "?") == true){
					window.open(location.origin + "/" + location.pathname.split("/")[1] + '/users/dm.do?num=' + event.senderUserCode);
				}else{
					//chekcing if event.sender exists as value
					console.log("---------------event.sender value : " + event.sender);
					console.log("replier id : " + '${id}');
					
					//denied notification sent to server to notify sender of deny.
					socket.emit("deny invitation", {targetUserId: ''+event.sender, replier: '${id}'});
				}
			}
		});
		
	</script>
	
	<jsp:include page="inc/footer.jsp" >
		<jsp:param value="true" name="timeline"/>
	</jsp:include>
</body>
</html>