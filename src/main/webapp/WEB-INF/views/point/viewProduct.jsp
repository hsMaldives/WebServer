<!-- viewProduct.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">

		<h2>Product Detail</h2>
		<!-- 		<h4>Here is detail information of the product</h4> -->
		<br />
		<div class="row">
			<div class="col-md-6">
				<img src="<c:url value="/resources/img/${product.imageFileName }"/>"
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

				

				<c:if test="${pageContext.request.userPrincipal.name != null }">
					<c:set var="role" value="${param.role }" />
					<c:set var="url" value="/point/products" />
					<c:if test="${role='admin'}">
						<c:set var="url" value="/admin/productInventory" />
					</c:if>

					<p>
						<a href="<c:url value="/point/pointShop"/>"
							class="btn btn-default">Back</a> <a
							href="<c:url value="/point/buyProduct/${product.id }"/>"
							class="btn btn-info">Let's Buy</a>

					</p>
				</c:if>

			</div>

		</div>
	</div>
</div>