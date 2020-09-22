<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="function">

		<form action="controller" method="post" name="f2">
			<input type="hidden" name="command" value="change_local"> 
			<input type="hidden" name="local" value="en">
			<button type="submit">english</button>
		</form>


		<form action="controller" method="post" name="f2">
			<input type="hidden" name="command" value="change_local">
			<input type="hidden" name="local" value="ru">
			<button type="submit">russian</button>
		</form>


		<form action="controller" method="get" name="f2">
			<input type="hidden" name="command" value="go_to_registration_page">
			<button type="submit">registration</button>
		</form>


		<form action="controller" method="get" name="f2">
			<input type="hidden" name="command" value="go_to_registration_page">
			<button type="submit">sign in</button>
		</form>


		<form action="controller" method="get" name="f2">
			<input type="hidden" name="command" value="go_to_registration_page">
			<button type="submit">sign out</button>
		</form>

	</div>

</body>
</html>