<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
	<head>
		<title>Hello Facebook</title>
	</head>
	<body>
		<h3>Connected to Facebook</h3>

		<p>
			You are now connected to your Facebook account.
			Click <a href="<c:url value="/"/>">here</a> to see some entries from your Facebook feed.
		</p>
	</body>
</html>