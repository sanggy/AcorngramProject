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
		<form action="post/write.do" id="write-post" enctype="multipart/form-data" method="post">
			<fieldset>
				<legend>
					<span>지금 내 앞엔?</span>
					<button>전송</button>
				</legend>
				<div class="flexbox">
					<label for="write-post-img">
						<input type="file" name="write-post-img" id="write-post-img" accept="image/*" required/>
						<div class="write-post__area">
							<i class="material-icons"> insert_photo </i>
							<p>클릭해서 사진 파일 업로드</p>
						</div>
						<img id="preview" width="360">
					</label>
					<textarea name="description" id="description" placeholder="내용" required></textarea>
				</div>
			</fieldset>
		</form>
		<ul>
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
		</ul>
	</main>
	<jsp:include page="inc/footer.jsp" />
	<script>
		$('#write-post-img').on('change', function (e) {
		    var reader = new FileReader();
		    reader.onload = function (e) {
		        $("#preview").attr('src', e.target.result);
		    }
		    reader.readAsDataURL(e.target.files[0]);
		});
	</script>
</body>
</html>