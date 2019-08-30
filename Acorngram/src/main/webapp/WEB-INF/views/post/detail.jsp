<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../inc/head.jsp">
		<jsp:param value="post/detail" name="title"/>
		<jsp:param value="post_detail" name="css"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="../inc/header.jsp" />
	<main>
		<nav class="detail__header">
			
		</nav>
		<article class="post post-${post.num }">
			<div class="post__img" style="
				background-image: url('${pageContext.request.contextPath}/upload/${post.saveFileName }')">
			</div>

			<div class="post__content">
				<div class="post__header">
					<div class="post__header-left">
						<img src="${post.profile_img }" alt="" class="post__icon"/>
						<hgroup>
							<h5 class="post__name"> ${post.nickname } </h5>
							<h6 class="post__id"> @${post.id } </h6>
						</hgroup>
					</div>
					<div class="post__header-right">
					<c:choose>
						<c:when test="${post.usercode eq usercode }">
							<a href="javascript:deletePost(${i})" role="button" class="post__btn-delete"> <i class="glyphicon glyphicon-trash"></i></a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="">
								<%-- 이 유저와 팔로우 상태라면 --%>
								<a href="javascript:unfollowToggle(${post.usercode })" role="button" class="post__btn-unfollow" ><i class="glyphicon glyphicon-remove-sign"></i> <span>Unfollow</span> </a>
								</c:when>
								<c:otherwise>
								<a href="javascript:followToggle(${post.usercode })" role="button" class="post__btn-follow"><i class="glyphicon glyphicon-plus-sign"></i> <span>Follow</span> </a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					</div>
				</div>
				<div class="post__info">
					<div class="post__like ">
						<c:choose>
							<c:when test="post.like">
							<%-- 이 게시글에 like 했다면 --%>
								<a href="javascript:likeControl(${i})" class="post__btn-like liked"><i class="glyphicon glyphicon-heart "></i></a>
							</c:when>
							<c:otherwise>
								<a href="javascript:likeControl(${i})" class="post__btn-like"><i class="glyphicon glyphicon-heart-empty "></i></a>
							</c:otherwise>
						</c:choose>
						<span class="count-like">${post.like_count }</span>
					</div>
					
					<div class="post__regdate">
						<time datetime="${post.regdate }"></time>
					</div>
				</div>
				<div class="post__body">
					<h3>${post.nickname } </h3>
					<p>${post.content }</p>
				</div>
				<div class="post__comment">
					<div class="comment__form">
						<form action="comment/write.do" method="post">
							<textarea name="content" id="comment-content"></textarea>
							<button>전송</button>
						</form>
					</div>
					<div class="comment__area">
						<ul class="comment__list">
						<c:forEach var="cmt" items="${commentList }">
							<%-- <c:choose>
								<c:when test="${cmt.deleted eq no }"> --%>
									<li>
										<p>
											<strong>${cmt.usercode } </strong>
											<span>${cmt.content } </span>
										</p>
										<p>
											<time datetime="${cmt.regdate }"></time>
											<span>
												<a href="comment/edit.do">수정</a>
												<a href="comment/delete.do">삭제</a>
											</span>
										</p>
									</li>
								<%-- </c:when>
								<c:otherwise>
									<li class="comment-deleted">삭제된 댓글 입니다.</li>
								</c:otherwise>
							</c:choose> --%>
						</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</article>
	</main>
	<jsp:include page="../inc/footer.jsp" />
</body>
</html>