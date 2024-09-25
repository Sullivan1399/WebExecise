<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager's Home</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.account == null}">
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<li><a href="${pageContext.request.contextPath}/register">Đăng ký</a></li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<li><a
						href="${pageContext.request.contextPath}/manager/home">${sessionScope.account.username}</a></li>
					<li><a href="${pageContext.request.contextPath }/logout">Đăng Xuất</a></li>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>