<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link href="<c:url value="/resources/css/star-rating.css" />" media="all" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/themes/krajee-svg/theme.css" />" media="all" rel="stylesheet" type="text/css" />
<script src="<c:url value="/resources/js/star-rating.js" />"></script>
<script src="<c:url value="/resources/themes/krajee-svg/theme.js"/>"></script>
<script src="<c:url value="/resources/js/locales/LANG.js" />"></script>

<div class="container">
	<h2>미평가 장소 평가</h2>
	<p>기존에 방문했던 미평가 장소를 평가합니다.</p>
	<form:form action="${pageContext.request.contextPath }/evaluation/post-evaluation" commandName="evaluation" method="POST">
		<div class="row">
			<div class="form-group col-lg-4">
				<label class="control-label" for="eva1">장소명:</label>
				<p>${position.store.name }</p>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-4">
				<label class="control-label" for="eva1">방문시간:</label>
				<p>${position.time }</p>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-lg-4">
				<label class="control-label" for="eva1">
					가격:</label>
				<form:input path="eva1" cssClass="rating rating-loading" data-min="0" data-max="5" data-step="0.5"/>
				<form:errors id="error-eva1" path="eva1" cssClass="help-block" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-4">
				<label class="control-label" for="eva2">
					맛:</label>
				<form:input path="eva2" cssClass="rating rating-loading" data-min="0" data-max="5" data-step="0.5" />
				<form:errors id="error-eva2" path="eva2" cssClass="help-block" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-4">
				<label class="control-label" for="eva3">
					위생:</label>
				<form:input path="eva3" cssClass="rating rating-loading" data-min="0" data-max="5" data-step="0.5" />
				<form:errors id="error-eva3" path="eva3" cssClass="help-block" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-4">
				<label class="control-label" for="eva4">
					친절:</label>
				<form:input path="eva4" cssClass="rating rating-loading" data-min="0" data-max="5" data-step="0.5" />
				<form:errors id="error-eva5" path="eva4" cssClass="help-block" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-4">
				<label class="control-label" for="eva5">
					양:</label>
				<form:input path="eva5" cssClass="rating rating-loading" data-min="0" data-max="5" data-step="0.5" />
				<form:errors id="error-eva5" path="eva5" cssClass="help-block" />
			</div>
		</div>
		
		<form:hidden path="positionIdx"/>
		<button type="submit" class="btn btn-default">
			평가
		</button>
	</form:form>
</div>