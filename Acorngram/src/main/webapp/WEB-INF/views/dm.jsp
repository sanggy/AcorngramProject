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
				<img src="${pageContext.request.contextPath}/${targetUser.profile_img }" alt="" class="post__icon"/>
				<hgroup>
					<h5 class="post__name"> ${targetUser.nickname } </h5>
					<h6 class="post__id"> @${targetUser.id } </h6>
				</hgroup>
			</div>
			<div class="dm__msg-list">
				<ul id="msg-list">
					
				</ul>
			</div>
			<div class="dm__footer">
				<textarea name="msg" id="dm-msg" maxlength="200"></textarea>
				<button type="button" onclick="sendMsg()">전송</button>
			</div>
		</article>
	</main>
	
	<script>
		//console.log('userid : ' + ${id});
		
		
		//socket.join("private/${targetUser.id}")
		
		//client
		//socket.emit("joinAction", "room 1");
		
		//server
		
		//socket.on("joinAction", function(roomName){
			//socket.join(roomName);
		//});
		
		const socket = io('http://172.30.1.56:3000');
		
		socket.on("connect", function(event){
			console.log("socket 연결되었습니다.");
			socket.emit("userConnected", {userId: '${id}'});
			//상대 유저아이디로 만들어진 방에 접속하기
			//socket.emit("/privateMsg/", {userId: '${id}', targetUserId: '${targetUser.id}'});
			//console.log("targetUserId" + '${targetUser.id}');
		});
		
		
		function sendMsg() {
			console.log("targetUserId: " + '${targetUser.id}')
			socket.emit('/privateMsg/', {msg: $('#dm-msg').val(), sender: '${id}', targetUserId: '${targetUser.id}', num: '${targetUserCode}', usercode: '${usercode}'});
	        //socket.emit('chat message', {msg: $('#dm-msg').val(), targetUser: '${targetUser.id}', user: '${id}'});
	        $('#dm-msg').val('');
	    }
		
		socket.on('/privateMsg/', function(data){
        	$('#msg-list').append($('<li>').text(data.sender + " : " + data.msg));
        });
		
		socket.on('User Offline', function(msg){
			$('#msg-list').append($('<li>').text("SYSTEM SENT MSG: " + msg));
		});
	</script>
	
	<jsp:include page="inc/footer.jsp" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.2.0/socket.io.js"></script>
	<script src=""></script>
</body>
</html>