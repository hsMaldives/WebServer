<!-- viewProduct.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/controller.js"/>"></script>

<div class="container-wrapper">
	<div class="container" ng-app="cartApp">

		<h2>Product Detail</h2>
		<h4>Here is detail information of the product</h4>
		<div class="row" ng-controller="cartCtrl">
			<div class="col-md-6">
				<img
					src="<c:url value="/resources/images/${product.imageFileName }"/>"
					alt="image" style="width: 80%" />
			</div>

			<div class="col-md-6">
				<h3>${product.name }</h3>
				<p>${product.description }</p>
				<p>
					<strong>Manufacturer</strong> : ${product.manufacturer }
				</p>
				<p>
					<strong>Category</strong> : ${product.category }
				</p>

				<h4>${product.price }원</h4>

				<br />

				<c:if test="${pageContext.request.userPrincipal.name != null }">
					<c:set var="role" value="${param.role }" />
					<c:set var="url" value="/products" />
					<c:if test="${role='admin'}">
						<c:set var="url" value="/admin/productInventory" />
					</c:if>

					<p>
						<a href="<c:url value="${url }"/>" class="btn btn-default">Back</a>
						<button class="btn btn-warning btn-large"
							ng-click="addToCart('${product.id }')">
							<span class="glyphicon glyphicon-shopping-cart"></span> Order Now
						</button>
						<a href="<c:url value="/cart"/>" class="btn btn-default"> <span
							class="glyphicon glyphicon-hand-right"></span> View Cart
						</a>
					</p>
				</c:if>

			</div>

		</div>
	</div>
</div>