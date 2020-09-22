<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="rb" />

<fmt:message bundle="${rb}" key="header.button.english" var="button_english"/>
<fmt:message bundle="${rb}" key="header.button.russian" var="button_russian"/>
<fmt:message bundle="${rb}" key="header.button.registration" var="button_registration"/>
<fmt:message bundle="${rb}" key="header.button.signin" var="button_signin"/>
<fmt:message bundle="${rb}" key="header.button.signout" var="button_signout"/>
<fmt:message bundle="${rb}" key="content.header.logo" var="logo"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<header class="header">
		<div class="container">
			<div class="header__inner">
				<div class="header_logo"><c:out value="${logo}"/></div>
				<div class="header_nav">
					<div class="action">
						<form action="controller" method="post" name="f1">
							<input type="hidden" name="command" value="change_local">
							<input type="hidden" name="local" value="en">
							<button type="submit">
								<c:out value="${button_english}"/>
							</button>
						</form>
					</div>
					<div class="action">
						<form action="controller" method="post" name="f1">
							<input type="hidden" name="command" value="change_local">
							<input type="hidden" name="local" value="ru">
							<button type="submit">
								<c:out value="${button_russian}"/>
							</button>
						</form>
					</div>
					<div class="action">
						<form action="controller" method="post" name="f1">
							<input type="hidden" name="command"	value="go_to_registration_page">
							<button type="submit">
								<c:out value="${button_registration}"/>
							</button>
						</form>
					</div>
					<div class="action">
						<form action="controller" method="post" name="f1">
							<input type="hidden" name="command"	value="go_to_sign_in_page">
							<button type="submit">
								<c:out value="${button_signin}"/>
							</button>
						</form>
					</div>
					<div class="action">
						<form action="controller" method="post" name="f1">
							<input type="hidden" name="command"	value="sign_out">
							<button type="submit">
								<c:out value="${button_signout}"/>
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</header>


</body>
</html>