<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/signin/facebook" method="POST">
		<input type="hidden" name="_csrf" value="${_csrf.token}">
		<div class="formInfo">
			<p>You aren't connected to Facebook yet. Click the button to
				connect this application with your Facebook account.</p>
		</div>
		<p>
			<button type="submit">Connect to Facebook</button>
		</p>
	</form>
</body>
</html>