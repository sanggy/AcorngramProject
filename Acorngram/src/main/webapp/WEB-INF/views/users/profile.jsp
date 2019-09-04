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
		<div class="profile profile-${user.usercode } container">
			<div class="profile__info">
				<div class="profile__info-left">
					<img src="${pageContext.request.contextPath}/${user.profile_img }" alt="${user.id }의 프로필 사진" class="profile__icon"/>
				</div>
				<div class="profile__info-right">
					<hgroup>
						<h5 class="post__name"> ${user.nickname } </h5>
						<h6 class="post__id"> @${user.id } </h6>
					</hgroup>
					<ul class="profile__user-count">
						<li class="profile__post-count">
							<data value="" class="after-newline">POST</data>
							<span>${postCount }</span>
						</li>
						<li class="profile__user-follow-count">
							<data value="" class="after-newline">FOLLOW</data>
							<span>15</span>
						</li>
						<li class="profile__user-follower-count">
							<data value="" class="after-newline">FOLLOWER</data>
							<span>15</span>
						</li>
					</ul>
				</div>
			</div>
			<p>주의: 팔로우버튼 동작안함 수정예정</p>
			<div class="profile__controller">
				<c:choose>
					<c:when test="${usercode eq user.usercode }">
						<%-- 자기 자신의 프로필일 경우 --%>
						<a href="settings.do?num=${usercode }" role="button" class="profile__btn-edit">프로필 수정</a>
					</c:when>
					<c:otherwise>
						<%-- 남의 프로필일 경우 --%>
						<c:choose>
							<c:when test="${followed }">
							<%-- 이 유저와 팔로우 상태라면 --%>
							<a href="javascript:followToggle(${user.usercode })" role="button" class="profile__btn-unfollow" ><i class="glyphicon glyphicon-remove-sign"></i> <span>Unfollow</span>  </a>
							</c:when>
							<c:otherwise>
							<a href="javascript:followToggle(${user.usercode })" role="button" class="profile__btn-follow"><i class="glyphicon glyphicon-plus-sign"></i> <span>Follow</span> </a>
							</c:otherwise>
						</c:choose>
						<a href="${pageContext.request.contextPath}/users/dm.do?num=${user.usercode }" role="button" class="profile__btn-dm"><i class="glyphicon glyphicon-send"></i> <span>DM</span></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		<!-- 유저 타임라인 -->
		
		<div class="timeline container">
			<c:if test="${not empty list }">
				<c:forEach var="post" items="${list }">
					<article class="post post-user-${post.usercode }" id="post-${post.num }">
						<div class="post__header">
							<div class="post__header-left">
								<a href="${pageContext.request.contextPath}/users/profile.do?id=${post.id }">
									<img src="${pageContext.request.contextPath}${post.profile_img }" alt="" class="post__icon"/>
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
										<c:when test="${post.followed }">
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
						<div class="post__img" style="
							background-image: url('${pageContext.request.contextPath}/upload/${post.saveFileName }')">
						</div>
						<div class="post__info">
							<div class="post__like">
								<c:choose>
									<c:when test="${post.liked}">
									<%-- 이 게시글에 like 했다면 --%>
										<a href="javascript:likeControl(${post.num})" class="post__btn-like liked"><i class="glyphicon glyphicon-heart "></i></a>
									</c:when>
									<c:otherwise>
										<a href="javascript:likeControl(${post.num})" class="post__btn-like"><i class="glyphicon glyphicon-heart-empty "></i></a>
									</c:otherwise>
								</c:choose>
								<span class="count-like">${post.like_count }</span>
								<span class="glyphicon glyphicon-comment">${post.commentCount}</span>
							</div>
							
							<div class="post__info-data">
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