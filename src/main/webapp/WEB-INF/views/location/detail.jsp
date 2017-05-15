<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<div class="container">
	<h2>${store.name } 상세정보</h2>
	<p>가로세로 변경예정</p>
	<table class="table table-striped">
		<thead>
			<tr>
				<th></th>
				<th>Store_idx</th>
				<th>Code</th>
				<th>Name</th>
				<th>Latitude</th>
				<th>Longitude</th>
				<th>Address</th>
				<th>Phone</th>
				<th>별점??등등</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><img src="${store.imageUrl }" /></td>
				<td>${store.storeIdx }</td>
				<td>${store.category.fullName }</td>
				<td>${store.name }</td>
				<td>${store.latitude }</td>
				<td>${store.longitude }</td>
				<td>${store.address }</td>
				<td>${store.phone }</td>
				<td>${store.direction }</td>
			</tr>

		</tbody>
	</table>
</div>