<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="rb" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing system</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<c:import url="/WEB-INF/jsp/header.jsp" />

	<div class="intro">
		<div class="container">
			<div class="intro_inner">

				<div class="intro_menu">
					<c:choose>

						<c:when test="${sessionScope.user.role eq 'admin' }">
							<c:import url="/WEB-INF/jsp/menu_admin.jsp" />
						</c:when>

						<c:when test="${sessionScope.user.role eq 'teacher' }">
							<c:import url="/WEB-INF/jsp/menu_teacher.jsp" />
						</c:when>

						<c:when test="${sessionScope.user.role eq 'student' }">
							<c:import url="/WEB-INF/jsp/menu_student.jsp" />
						</c:when>


					</c:choose>
				</div>

				<div class="intro_content">
					
					<c:if test="${not empty message}">
						<fmt:message bundle="${rb}" key="${message}" var="mess" />
						<h5 id="content_message"><c:out value="${mess}" /></h5>
					</c:if>

					<c:if test="${not empty content}">
						<c:import url="${content}" />
					</c:if>

				</div>

				<div class="intro_info">

					<c:choose>

						<c:when test="${sessionScope.user.role eq 'admin' }">
							<c:import url="/WEB-INF/jsp/info_admin.jsp" />
						</c:when>

						<c:when test="${sessionScope.user.role eq 'teacher' }">
							<c:import url="/WEB-INF/jsp/info_teacher.jsp" />
						</c:when>

						<c:when test="${sessionScope.user.role eq 'student' }">
							<c:import url="/WEB-INF/jsp/info_student.jsp" />
						</c:when>

					</c:choose>

				</div>

			</div>
		</div>
	</div>



</body>
</html>