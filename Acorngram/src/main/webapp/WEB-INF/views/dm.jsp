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
				<div class="dm-target__icon">
					<img src="${pageContext.request.contextPath}/${targetUser.profile_img }" alt=""/>
				</div>
				<hgroup class="dm-target__info">
					<h5 class="dm-target__name"> ${targetUser.nickname } </h5>
					<h6 class="dm-target__id"> @${targetUser.id } </h6>
				</hgroup>
			</div>
			<div class="dm__msg-list">
				<ul id="msg-list">
					
				</ul>
			</div>
			<div class="dm__footer">
				<textarea name="msg" id="dm-msg" maxlength="200"></textarea>
				<button type="button" id="btn_send">전송</button>
			</div>
		</article>
	</main>
	
	<jsp:include page="inc/footer.jsp" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.2.0/socket.io.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/dm.min.js"></script>
	<script>
		window.addEventListener('load',e=>{
			const msg = document.getElementById('dm-msg');
			
			//const socket = io(location.hostname+':3000');
			const socket = io('http://192.168.0.93:3000');

			const mine = {};
			mine.id = '${id}';
			mine.code = '${usercode}';
			mine.target = {
				id : '${targetUser.id}',
				code : '${targetUserCode}'
			};

			socket.on("connect", e=>{
				console.log("socket 연결되었습니다.");
				socketFunction.socket = socket;
				socketFunction.onconnect(mine);
			});

			document.getElementById('btn_send').addEventListener('click',send);
			document.getElementById('dm-msg').addEventListener('keydown',e=>{
				if(e.ctrlKey&&e.code==="Enter") send(e);
				else if(msg.value.length===0&&e.ctrlKey&&e.code==="KeyZ") msg.value = sessionStorage.getItem("undo");
			});
			
			function send(e){
				socketFunction.onsendmsg(mine,msg.value);
				sessionStorage.setItem("undo", msg.value);
				msg.value="";
			}
		
			socket.on('/privateMsg/', function(data){
				//chekcing if event.sender exists as value
				if(data.targetUser === mine.id && mine.target.id !== data.sender){
					let msg = data.sender+"님의 채팅 초대입니다. 확인하시겠습니까?";
					if(confirm(msg)){
						window.open(location.origin + "/" + location.pathname.split("/")[1] + '/users/dm.do?num=' + data.senderUserCode);
					}else{
						//denied notification sent to server to notify sender of deny.
						socketFunction.ondenyinvite(data.sender, mine.id);
					}
				}else socketFunction.onreceivemsg.private(mine, data);
			});
			
			socket.on('User Offline', function(msg){
				socketFunction.onreceivemsg.offline(msg);
			});
			
			

		});
	</script>
</body>
</html>