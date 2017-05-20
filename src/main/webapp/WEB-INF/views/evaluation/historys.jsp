<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">
	<h2>기존 평가 장소 조회</h2>
	<p>기존에 평가했던 장소의 평점을 조회할 수 있습니다</p>
	<c:forEach var="evaluation" items="${evaluations}">
		<table class="table table-bordered center-text">
			<tr class="info">
				<th>장소명</th>
				<td>${evaluation.position.store.name }</td>
			</tr>
			<tr>
				<th>No</th>
				<td>${evaluation.positionIdx }</td>
			</tr>
			<tr>
				<th>eva1</th>
				<td>${evaluation.eva1 }</td>
			</tr>
			<tr>
				<th>eva2</th>
				<td>${evaluation.eva2 }</td>
			</tr>

			<tr>
				<th>eva3</th>
				<td>${evaluation.eva3 }</td>
			</tr>
			<tr>
				<th>eva4</th>
				<td>${evaluation.eva4 }</td>
			</tr>
			<tr>
				<th>eva5</th>
				<td>${evaluation.eva5 }</td>
			</tr>
			<tr>
				<th>방문시간</th>
				<td>${evaluation.position.time }</td>
			</tr>



		</table>
		<br />
	</c:forEach>
</div>