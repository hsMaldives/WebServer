<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script
	src="${pageContext.request.contextPath}/resources/js/filtering.js"></script>


<section id="protfolio_sec">
	<div class="container">
		<div class="">
			<h2>Point Shop</h2>

			<p class="lead">착한 가격에 만나보세요!!</p>
		</div>



		<div class="">
			<table class="table table-striped" style="width: 100%">
				<thead>
					<tr class="bg-success">
						<th>사용가능 포인트</th>
						<th>사용한 포인트</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<td>${point }point</td>
					<td>##</td>
					<td></td>
					<td></td>
				</tbody>
			</table>


		</div>

		<br />


		<div>


			<p>제품을 클릭하면 상세정보를 볼 수 있습니다.</p>
			<%-- 			<c:forEach var="product" items="${products }"> --%>
			<!-- 				<button class="btn btn-default filter-button" -->
			<%-- 					data-filter="'${product.category }'">${product.category }</button> --%>
			<%-- 			</c:forEach> --%>
			<button class="btn btn-primary filter-button" data-filter="all">All</button>
			<button class="btn btn-default filter-button" data-filter="giftcard">상품권</button>
			<button class="btn btn-default filter-button" data-filter="event">이벤트</button>
		</div>
		<br />




		<div class="row">
			<c:forEach var="product" items="${products }">
				<div class="col-md-3 filter ${product.category }">
					<div class="each-item">
						<img class="port-image"
							src="<c:url value="/resources/img/${product.imageFileName }"/>" />
						<div class="cap1">
							<h3>제품명 : ${product.name }</h3>
							<p>제조사 : ${product.manufacturer }</p>
							<p>가격 : ${product.price } point</p>
						</div>
						<div class="cap2">
							<a href="<spring:url value="/point/viewProduct/${product.id }"/>">

								<br />
								<p class="text-center">
									<span class="glyphicon glyphicon-info-sign"></span>Show Detail
								</p>
							</a>
						</div>

					</div>

				</div>

			</c:forEach>

		</div>
	</div>
</section>
<!-- End Portfolio Section -->

