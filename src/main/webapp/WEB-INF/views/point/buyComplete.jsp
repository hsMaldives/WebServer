<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="container">

	<h2>"${product.name }" 결제완료되었습니다!!!!</h2>
	<div class="">
		<table class="table table-striped" style="width: 100%">
			<thead>
				<tr class="bg-success">
					<th>사용가능 포인트</th>
					<th>사용한 포인트</th>
				</tr>
			</thead>
			<tbody>
				<td>${user.point }point</td>
				<td>${spendedPoint}point</td>
			</tbody>
		</table>
	</div>


	<div class="row">
		 <br /> <a
			href="<c:url value="/"/>" class="btn btn-default">메인화면으로 가기</a> <a
			href="<c:url value="/point/pointShop"/>" class="btn btn-default">쇼핑
			더하기</a>

	</div>
</div>
