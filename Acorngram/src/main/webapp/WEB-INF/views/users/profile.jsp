<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../inc/head.jsp">
		<jsp:param value="Profile" name="title"/>
		<jsp:param value="profile" name="css"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="../inc/header.jsp" />
	<main>
		<!-- 유저 프로필 -->
		<div class="profile container">
			<div class="profile__icon">
				<img src="${pageContext.request.contextPath}/${user.profile_img }" alt="${user.id }의 프로필 사진" class="profile__icon"/>
			</div>
			<div class="profile__info">
				<hgroup>
					<h5 class="post__name"> ${user.nickname } </h5>
					<h6 class="post__id"> @${user.id } </h6>
				</hgroup>
				<ul class="profile__user-count">
					<li class="profile__post-count"></li>
					<li class="profile__user-follow-count"></li>
					<li class="profile__user-follower-count"></li>
				</ul>
			</div>
			<div class="profile__controller">
				<c:choose>
					<c:when test="${usercode eq user.usercode }">
						<%-- 자기 자신의 프로필일 경우 --%>
						<a href="settings.do?num=${usercode }" role="button">프로필 수정</a>
					</c:when>
					<c:otherwise>
						<%-- 남의 프로필일 경우 --%>
						<c:choose>
							<c:when test="">
							<%-- 이 유저와 팔로우 상태라면 --%>
							<a href="javascript:followToggle(${user.usercode })" role="button" class="post__btn-unfollow" ><i class="glyphicon glyphicon-remove-sign"></i> <span>Unfollow</span>  </a>
							</c:when>
							<c:otherwise>
							<a href="javascript:followToggle(${user.usercode })" role="button" class="post__btn-follow"><i class="glyphicon glyphicon-plus-sign"></i> <span>Follow</span> </a>
							</c:otherwise>
						</c:choose>
						<a href="users/dm.do?num=${user.usercode }" role="button" class="btn-dm"><span>DM</span></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		<!-- 유저 타임라인 -->
		
		<div class="timeline container">
			<c:if test="${not empty list }">
				<c:forEach var="post" items="${list }">
					<article class="post" id="post-${post.num }">
						<div class="post__header">
							<div class="post__header-left">
								<a href="users/profile.do?num=${post.usercode }">
									<img src="" alt="" class="post__icon"/>
									<hgroup>
										<h5 class="post__header-name"> ${post.usercode } </h5>
										<h6 class="post__header-id"> @${post.id } </h6>
									</hgroup>
								</a>
							</div>
							<div class="post__header-right">
							<c:choose>
								<c:when test="${post.usercode eq usercode }">
									<a href="javascript:deletePost(${post.num })" role="button" class="post__btn-delete"> <i class="glyphicon glyphicon-trash"></i></a>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="">
										<%-- 이 유저와 팔로우 상태라면 --%>
										<a href="javascript:followToggle(${post.usercode })" role="button" class="post__btn-unfollow" ><i class="glyphicon glyphicon-remove-sign"></i> <span>Unfollow</span>  </a>
										</c:when>
										<c:otherwise>
										<a href="javascript:followToggle(${post.usercode })" role="button" class="post__btn-follow"><i class="glyphicon glyphicon-plus-sign"></i> <span>Follow</span> </a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
							</div>
						</div>
						<div class="post__img" style="
							background-image: url('${pageContext.request.contextPath}/upload/${post.saveFileName }')">
						</div>
						<div class="post__info">
							<div class="post__like">
								<c:choose>
									<c:when test="">
									<%-- 이 게시글에 like 했다면 --%>
										<a href="javascript:likeControl(${post.num })" class="post__btn-like"><i class="glyphicon glyphicon-heart "></i></a>
									</c:when>
								<c:otherwise>
									<a href="javascript:likeControl(${post.num })" class="post__btn-like "><i class="glyphicon glyphicon-heart-empty "></i></a>
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
					</article>
				</c:forEach>
			</c:if>
		</div>		
	</main>
	<jsp:include page="../inc/footer.jsp" />
</body>
</html>