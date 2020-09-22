<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="rb" />

<fmt:message bundle="${rb}" key="content.signin.title" var="title"/>
<fmt:message bundle="${rb}" key="input.login" var="login"/>
<fmt:message bundle="${rb}" key="input.password" var="password"/>
<fmt:message bundle="${rb}" key="button.reset" var="reset"/>
<fmt:message bundle="${rb}" key="button.submit" var="submit"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<div class="registration">
		<form action="controller" method="post" name="form_registration">
			<input type="hidden" name="command" value="sign_in">
			<h3 id="title_registration"><c:out value="${title}"/></h3>
			<div class="group">
				<label for=""><c:out value="${login}"/></label> <input type="text" name="login" value="">
			</div>
			<div class="group">
				<label for=""><c:out value="${password}"/></label> <input type="password" name="password" value="">
			</div>
			<div class="group">
				<button id="reg_b_reset" type="reset"><c:out value="${reset}"/></button>
				<button id="reg_b_submit" type="submit"><c:out value="${submit}"/></button>
			</div>
		</form>
	</div>

</body>
</html>