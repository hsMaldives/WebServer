<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<h2>오류 발생</h2>
	<p>예상치 못한 오류가 발생하였습니다.</p>
	<p>(${statusCode })</p>
	
	<a href="<c:url value="/"/>" class="btn btn-default">홈으로 가기</a>
</div>