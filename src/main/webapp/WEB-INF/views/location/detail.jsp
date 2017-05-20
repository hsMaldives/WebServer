<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container-wrapper">

	<div class="container">

		<h2>Recommend Detail Page</h2>
		<p class="lead"></p>

		<div class="col-xs-12">

			<div class="">
				<img class="center-block img-rounded " src="<c:url value="${store.imageUrl }"/>"
					alt="image" style="width: 80%" />
			</div>
			
			<br />
			
			<table class="table table-striped">
				<tbody>
					<tr>
						<th>name</th>
						<td>${store.name }</td>
					</tr>
					<tr>
						<th>category</th>
						<td>${store.category.name }</td>
					</tr>
					<tr>
						<th>phone</th>
						<td>${store.phone }</td>
					</tr>
					<tr>
						<th>address</th>
						<td>${store.address }</td>
					</tr>
					<tr>
						<th>latitude</th>
						<td>${store.latitude }</td>
					</tr>
					<tr>
						<th>longitude</th>
						<td>${store.longitude }</td>
					</tr>

					<tr>
						<th>평균평점</th>
						<td>${average}</td>
					</tr>

					<!--                <tr> -->
					<!--                   <th>1</th> -->
					<%--                   <td>${eval.eva1 }</td> --%>
					<!--                </tr> -->
					<!--                <tr> -->
					<!--                   <th>2</th> -->
					<%--                   <td>${eval.eva2 }</td> --%>
					<!--                </tr> -->
					<!--                <tr> -->
					<!--                   <th>3</th> -->
					<%--                   <td>${eval.eva3 }</td> --%>
					<!--                </tr> -->
					<!--                <tr> -->
					<!--                   <th>4</th> -->
					<%--                   <td>${eval.eva4 }</td> --%>
					<!--                </tr> -->
					<!--                <tr> -->
					<!--                   <th>5</th> -->
					<%--                   <td>${eval.eva5 }</td> --%>
					<!--                </tr> -->

				</tbody>
			</table>

			## 지도를 넣어주세요 ##

		</div>
	</div>
</div>





