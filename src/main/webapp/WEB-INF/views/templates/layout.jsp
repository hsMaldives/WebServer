<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">
	
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<link rel="icon" href="../../favicon.ico">
	
	<title><tiles:insertAttribute name="title"/></title>
	
	<!-- Bootstrap core CSS -->
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	
	<link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet">
	
	<!-- Custom styles for this template -->
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/main.js"/>"></script>
	
	<script>
		$(function () {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
		});
	</script>
</head>
<body>
	<div>
		<tiles:insertAttribute name="header"/>
	</div>
	<div id="main-content" class="container-fluid">
		<tiles:insertAttribute name="body"/>
	</div>
	<div>
		<tiles:insertAttribute name="footer"/>
	</div>
</body>
</html>
