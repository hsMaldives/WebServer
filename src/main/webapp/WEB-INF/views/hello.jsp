<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
	<head>
		<title>Hello Facebook</title>
	</head>
	<body>
		<h3>Hello, <span> ${facebookProfile.name} </span>!</h3>

		<h4>Here is your feed:</h4>

		<div>
			<c:forEach var="post" items="${feed}">
			
			</c:forEach>
			<b>${post.from.name}</b> wrote:
			<p>${post.message}</p>
			<c:if test="${not empty post.message}">
				<img src="${post.picture}"/>
			</c:if>
			<hr/>
		</div>
	</body>
</html>