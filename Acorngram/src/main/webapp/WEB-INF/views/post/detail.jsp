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
		<div class="container">
			<nav class="detail__header">
				<a href="${pageContext.request.contextPath}/timeline.do"><i class="glyphicon glyphicon-chevron-left"></i> 돌아가기</a>
			</nav>
			<article class="post" id="post-${post.num }">
				<div class="post__img" style="
					background-image: url('${pageContext.request.contextPath}/upload/${post.saveFileName }')">
				</div>

				<div class="post__content">
					<div class="post__header">
						<div class="post__header-left">
							<a href="${pageContext.request.contextPath}/users/profile.do?id=${post.id }">
								<img src="${pageContext.request.contextPath}/${post.profile_img }" alt="" class="post__icon"/>
								<hgroup>
									<h5 class="post__name"> ${post.nickname } </h5>
									<h6 class="post__id"> @${post.id } </h6>
								</hgroup>
							</a>
						</div>
						<div class="post__header-right">
						<c:choose>
							<c:when test="${post.usercode eq usercode }">
								<a href="javascript:deletePost(${post.num})" role="button" class="post__btn-delete"> <i class="glyphicon glyphicon-trash"></i></a>
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
								<c:when test="${post.liked }">
								<%-- 이 게시글에 like 했다면 --%>
									<a href="javascript:likeControl(${post.num})" class="post__btn-like liked"><i class="glyphicon glyphicon-heart "></i></a>
								</c:when>
								<c:otherwise>
									<a href="javascript:likeControl(${post.num})" class="post__btn-like"><i class="glyphicon glyphicon-heart-empty "></i></a>
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
							<form action="${pageContext.request.contextPath}/comment/write.do" method="post" class="comment-form">
								<input type="hidden" name="num" value="${post.num }" />
								<input type="hidden" name="ref_group" value="${post.num }" />
								<input type="hidden" name="target_code" value="${post.usercode }" />
								<textarea name="content" class="comment-content"></textarea>
								<button>전송</button>
							</form>
						</div>
						<div class="comment__area">
							<ul class="comment__list">
							<c:forEach var="cmt" items="${commentList }">
								<%-- <c:choose>
									<c:when test="${cmt.deleted eq no }"> --%>
										<li>
											<div class="comment__body">
												<strong>${cmt.usercode } </strong>
												<span>${cmt.content } </span>
											</div>
											<div class="comment__info">
												<time datetime="${cmt.regdate }"></time>
												<a href="javascript:" class="comment__link-reply">답글</a>
												<span>
													<a href="comment/delete.do?num=${cmt.num }">삭제</a>
												</span>
											</div>
											<div class="comment__form">
												<form action="${pageContext.request.contextPath}/comment/write.do" method="post" class="comment-form">
													<input type="hidden" name="num" value="${post.num }" />
													<input type="hidden" name="ref_group" value="${post.num }" />
													<input type="hidden" name="target_code" value="${post.usercode }" />
													<textarea name="content" class="comment-content"></textarea>
													<button>전송</button>
												</form>
											</div>
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
		</div>
	</main>
	<jsp:include page="../inc/footer.jsp" />
</body>
</html>