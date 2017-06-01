<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script>
	var map;
	var marker;
	var infowindow;

	function initialize() {
		infowindow = new google.maps.InfoWindow();
		var location = new google.maps.LatLng(${store.latitude}, ${store.longitude});
		var mapOptions = {
			zoom : 15,
			center : location,
			scaleControl : true,
			mapTypeId : 'roadmap'
		};

		map = new google.maps.Map(document.getElementById("map_contents"),
				mapOptions);
		
		marker = new google.maps.Marker({
			position : new google.maps.LatLng(${store.latitude}, ${store.longitude}),
			icon: '<c:url value="/resources/img/marker1.png" />',
			title : '${store.name}',
			category : '${store.category.name}',							
			map : map
		});
		
		google.maps.event.addListener(marker, 'click', function() {
			marker.setIcon('<c:url value="/resources/img/marker2.png" />');
			infowindow.close();		
			infowindow.setContent("<strong>"
					+ marker.title + "</strong>"
					+ "<p>"+marker.category+"</p>");
			infowindow.open(map, marker);
		});
	}
	
</script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=${googleMapApiKey}&amp;callback=initialize"></script>
<script src="<c:url value="/resources/js/owl.carousel.min.js" />"></script>

<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/owl.carousel.min.css" />" />
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/owl.theme.default.min.css" />" />

<script>
	$(function (){
		$('.owl-carousel').owlCarousel({
		    loop:true,
		    margin:10,
		    nav:true,
		    responsive:{
		        0:{
		            items:1
		        },
		        600:{
		            items:3
		        },
		        1000:{
		            items:5
		        }
		    }
		});
	});
		
</script>


<div class="container-wrapper">

	<div class="container">

		<h2>Recommend Detail Page</h2>
		<p class="lead"></p>

		<div class="col-xs-12">

			<div class="">
				<img class="center-block img-rounded " src="${store.imageUrl }"
					onerror="this.style='display: none;'" alt="image"
					style="width: 80%" />
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
						<td>${store.category.fullName }</td>
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
						<th>평균평점</th>
						<td><fmt:formatNumber value="${store.avgEvaluation }" pattern=".00"/></td>
					</tr>
				</tbody>
			</table>
			
			<div class="association-container">
				<h3>연관매장</h3>
				<div class="association-list owl-carousel owl-theme">
					<c:forEach var="store" items="${associationStores }">
						<div class="item">
							<a href="<c:url value="/location/detail/${store.storeIdx }" />">
								<div class="image-container">
									<img class="center-block img-rounded " src="${store.imageUrl }" onerror="this.src='<c:url value="/resources/img/main_logo.png" />'" alt="연관매장 사진" />
								</div>
								
								<p class="store-name text-center">
									${store.name }
								</p>
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
			<div id="map_contents" style="width: 100%; height: 300px"></div>
			<ul class="media-list margin-top-20">
				<c:forEach var="comment" items="${store.comments }">
					<li class="media"
						style="border-bottom: 1px solid #eee; margin-bottom: 15px; padding-bottom: 10px;">
						<div class="media-body">
							<div>
								<h4 class="media-heading"
									style="display: inline-block; margin-right: 20px;">${comment.user.nickname }</h4>
								<span class="pull-right">${comment.timestamp }</span>
							</div>
							<span><c:out value="${comment.comment }" /></span>
							<c:if
								test="${comment.user.email eq pageContext.request.userPrincipal.name }">
								<span class="glyphicon glyphicon-remove pull-right"
									aria-hidden="true"
									onclick="deleteComment(${comment.storeCommentIdx })"></span>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>

			<form id="comment-form"
				action="<c:url value="/location/detail/${store.storeIdx}/comment/" />"
				method="POST">
				<div class="input-group">
					<input type="text" class="form-control" name="comment"
						placeholder="댓글을 남겨주세요"> <span
						class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<span class="glyphicon glyphicon-pencil"></span>

						</button>
					</span>
				</div>

			</form>

			<script>
				$(function (){
					$('#comment-form').submit(function (event){
						$.ajax({
							url: $('#comment-form').attr('action'),
							method: 'POST',
							data: $('#comment-form').serialize(),
							success: function(data){
								alert("댓글이 성공적으로 전송되었습니다.");
								location.reload()
							},
							error: function (error){
								alert("[Error] 댓글 전송중 오류가 발생하였습니다.");
							}
						});
						
						event.preventDefault();
					});
				});
				
				function deleteComment(commentIdx){
					$.ajax({
						url: '<c:url value="/location/detail/${store.storeIdx}/comment/" />' + commentIdx,
						method: 'DELETE',
						success: function(data){
							alert("댓글이 성공적으로 삭제되었습니다.");
							location.reload()
						},
						error: function (error){
							alert("[Error] 댓글 삭제중 오류가 발생하였습니다.");
						}
					});
				}
			</script>
		</div>
	</div>
</div>





