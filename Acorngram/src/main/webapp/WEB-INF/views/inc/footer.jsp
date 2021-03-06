<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<footer>
	
</footer>
<!--[if IE]> 
<script src="${pageContext.request.contextPath}/resources/js/modernizr-custom.js"></script>
<script crossorigin="anonymous" src="https://polyfill.io/v3/polyfill.min.js?features=es5%2CJSON%2CNodeList.prototype.forEach%2CPromise%2CString.prototype.includes%2Cfetch%2C%7Ehtml5-elements%2C%7Eviewport"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<%-- javascript time --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.26/moment-timezone.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js"></script>
<%-- save contextpath to Web Stroge --%>
<script>
	sessionStorage.setItem("cpath", '${pageContext.request.contextPath}');
</script>
<%-- croppie.js --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.4/croppie.min.js"></script>
<%-- core.js --%>
<script src="${pageContext.request.contextPath}/resources/js/core.js"></script>
<c:if test="${param.timeline eq true}">
	<div class="loading"></div>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.bottom-1.0.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/tl.min.js"></script>
</c:if>

