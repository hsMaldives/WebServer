<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<h2>미평가 장소 조회</h2>
	<p>기존에 평가하지 않았던 장소를 조회할 수 있습니다</p>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>No</th>
				<th>장소명</th>
				<th>방문시간</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach var="position" items="${positions}">
			<tr>
				<td>${position.positionIdx }</td>
				<td>${position.store.name }</td>
				<td>${position.time }</td>
				<td>
					<a href="<c:url value="/evaluation/post-evaluation?position_idx=${position.positionIdx }"/>">
						<span class="glyphicon glyphicon-check" aria-hidden="true"></span>
					</a>
				</td>
			</tr>
		  </c:forEach>
		</tbody>
	</table>
</div>