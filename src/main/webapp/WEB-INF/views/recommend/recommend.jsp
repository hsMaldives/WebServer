
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container-wrapper">

	<div class="container">

		<h2>Item-based</h2>
		<p class="lead">아이템 기반 추천</p>

		<div class="col-xs-12">
			<c:forEach var="store" items="${stores}">
				<table class="table table-striped">

					<thead>
						<tr class="bg-success">
							<th>매장명 : ${store.name }</th>
							<th class="bg-warning">#카테고리#${store.category.name }</th>
						</tr>
					</thead>
					<tbody>

						<tr>
							<td colspan="2"><a
								href="<c:url value="/recommend/detail/${store.storeIdx }"/>">
									<img src="${store.imageUrl }" alt="image" style="width: 80%" class="center-block img-rounded" />
							</a></td>

						</tr>
					</tbody>
				</table>
			</c:forEach>
		</div>
	</div>
</div>