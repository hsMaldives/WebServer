<!-- products.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container-wrapper">

<div class="container">
	<h2>Point Shop</h2>
	<p class="lead">착한 가격에 만나보세요!!</p>
	<div class="">



		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>현재 보유 포인트</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<td>${point }</td>
				<td></td>
				<td></td>
				<td></td>
			</tbody>
		</table>


	</div>
	
	<br/>



	
		<h2>All Products</h2>
		<p class="lead">골라골라</p>

		<div class="col-xs-12">
			<table class="table table-striped">
				<thead>
					<tr class="bg-success">
						<th>Photo Thumb</th>
						<th>Product Name</th>
						<th>Category</th>
						<th>Price</th>
						<th>Manufacturer</th>
						<th>UnitInStock</th>
						<th>Description</th>
						<th>show detail</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${products}">
						<tr>
							<td><img
								src="<c:url value="/resources/img/${product.imageFileName }"/>"
								alt="image" style="width: 80%" /></td>
							<td>${product.name}</td>
							<td>${product.category}</td>
							<td>${product.price}</td>
							<td>${product.manufacturer}</td>
							<td>${product.unitInStock}</td>
							<td>${product.description}</td>
							<td><a
								href="<spring:url value="/point/viewProduct/${product.id }"/>"> <span
									class="glyphicon glyphicon-info-sign"></span>
							</a></td>
						</tr>
	
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>






