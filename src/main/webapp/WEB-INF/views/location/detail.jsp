<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<div class="container">
	<h2>${store.name } 상세정보</h2>
	<p>가로세로 변경예정</p>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Store_idx</th>
				<th>Code</th>
				<th>Name</th>
				<th>Latitude</th>
				<th>Longitude</th>
				<th>Address</th>
				<th>별점??등등</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${store.store_idx }</td>
				<td>${store.code }</td>
				<td>${store.name }</td>
				<td>${store.latitude }</td>
				<td>${store.longitude }</td>
				<td>${store.address }</td>
				<td>#</td>
			</tr>

		</tbody>
	</table>
</div>