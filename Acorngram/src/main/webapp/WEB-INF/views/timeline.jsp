<%@page import="java.util.Date"%>
<%@page import="com.acorngram.project.dto.PostDto"%>
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
			<c:if test="${not empty list }">
				<c:forEach var="post" items="list">
					<article class="post post-${post.num }">
						<div class="post__header">
							<img src="" alt="" class="tl__icon"/> <!-- icon -->
							<hgroup>
								<h5> ${post.name } </h5>
								<h6> @${post.id } </h6>
							</hgroup>
							<c:choose>
								<c:when test="${post.usercode eq usercode }">
									<a href="javascript:deletePost(${post.num })" class="glyphicon glyphicon-trash"> delete </a>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="">
											<%-- 이 유저와 팔로우 상태라면 --%>
											<a href="javascript:followToggle(${post.usercode })" class="glyphicon glyphicon-remove-sign"> Unfollow </a>
										</c:when>
										<c:otherwise>
											<a href="javascript:followToggle(${post.usercode })" class="glyphicon glyphicon-plus-sign"> Follow </a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</div>
						
						<div class="post__img" style="
							background-image: url('${pageContext.request.contextPath}/upload/${post.image }')">
						</div>
						<div class="post__info">
							<div class="post__like">
								<c:choose>
									<c:when test="">
										<%-- 이 게시글에 like 했다면 --%>
										<a href="javascript:likeControl(${post.num })" class="glyphicon glyphicon-heart "></a>
									</c:when>
									<c:otherwise>
										<a href="javascript:likeControl(${post.num })" class="glyphicon glyphicon-heart-empty "></a>
									</c:otherwise>
								</c:choose>
								<span class="count-like">${post.like_count }</span>
							</div>
							<div class="post__regdate">
								<time datetime="${post.regdate }"> </time>
							</div>
						</div>
						<div class="post__body">
							<h3>${post.name } </h3>
							<p>${post.content }</p>
						</div>
					</article>
				</c:forEach>
			</c:if>
			
			<article class="post post-${post.num }">
				<div class="post__header">
					<div class="post__header-left">
						<img src="" alt="" class="post__icon"/>
						<hgroup>
							<h5 class="post__header-name"> 이름 </h5>
							<h6 class="post__header-id"> @id </h6>
						</hgroup>
					</div>
					<div class="post__header-right">
							<a href="javascript:deletePost(${post.num })" role="button" class="post__btn-delete"> <i class="glyphicon glyphicon-trash"></i></a>
								<%-- 이 유저와 팔로우 상태라면 --%>
								<a href="javascript:followToggle(${post.usercode })" role="button" class="post__btn-unfollow" ><i class="glyphicon glyphicon-remove-sign"></i> Unfollow </a>
								<a href="javascript:followToggle(${post.usercode })" role="button" class="post__btn-follow"><i class="glyphicon glyphicon-plus-sign"></i> Follow </a>
					</div>
				</div>
				
				<div class="post__img" style="
					background-image: url('${pageContext.request.contextPath}/upload/${post.image }')">
				</div>
				<div class="post__info">
					<div class="post__like">
							<%-- 이 게시글에 like 했다면 --%>
							<a href="javascript:likeControl(${post.num })" class="post__btn-like"><i class="glyphicon glyphicon-heart "></i></a>
							<a href="javascript:likeControl(${post.num })" class="post__btn-like "><i class="glyphicon glyphicon-heart-empty "></i></a>
						<span class="count-like">0</span>
					</div>
					<div class="post__regdate">
						<time datetime="2019/08/27T15:32:45"></time>
					</div>
				</div>
				<div class="post__body">
					<h3>이름 </h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quasi eligendi repellat soluta illum necessitatibus atque aliquam dolores vitae mollitia nisi delectus pariatur rerum doloremque earum assumenda nostrum impedit totam laborum!</p>
				</div>
			</article>
			
		</div>
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