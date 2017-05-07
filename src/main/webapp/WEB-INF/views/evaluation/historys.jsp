<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<h2>기존 평가 장소 조회</h2>
	<p>기존에 평가했던 장소의 평점을 조회할 수 있습니다</p>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>No</th>
				<th>장소명</th>
				<th>eva1</th>
				<th>eva2</th>
				<th>eva3</th>
				<th>eva4</th>
				<th>eva5</th>
				<th>방문시간</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach var="evaluation" items="${evaluations}">
			<tr>
				<td>${evaluation.position_idx }</td>
				<td>${evaluation.position.store.name }</td>
				<td>${evaluation.eva1 }</td>
				<td>${evaluation.eva2 }</td>
				<td>${evaluation.eva3 }</td>
				<td>${evaluation.eva4 }</td>
				<td>${evaluation.eva5 }</td>
				<td>${evaluation.position.time }</td>
			</tr>
		  </c:forEach>
		</tbody>
	</table>
</div>