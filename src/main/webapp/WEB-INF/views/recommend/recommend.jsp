<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="row">
	<div class="col-xs-12">
		<div id="map-contents" style="width: 100%; height: 400px"></div>
	</div>
</div>

<div class="container-wrapper">

	<div class="container">
		<h2>Item-based</h2>
		<p class="lead">아이템 기반 추천</p>

		<div class="list-group list-store">
			<c:forEach var="store" items="${ibcfStores}" varStatus="status">
				<div class="list-group-item">
					<div class="media-left">
						<img class="media-object" src="${store.imageUrl}" onerror="this.src='<c:url value="/resources/img/main_logo.png" />'" alt="매장사진">
					</div>
					<div class="media-body">
						<h4 class="media-heading">
							<a href="<c:url value="/location/detail/${store.storeIdx }"/>">${store.name }</a>
						</h4>
						<p class="list-group-item-text">
							<a href="#" onclick="showMapStoreInfo('ibcf', ${status.index})">${store.address }</a>
						</p>
					</div>
					<div class="media-right">
						<p class="text-right evaluation">
							<c:if test="${not empty store.avgEvaluation }">
								<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
								<fmt:formatNumber value="${store.avgEvaluation }" pattern=".00"/>
							</c:if>
						</p>
						<p class="text-right"><span class="badge">${store.category.name }</span></p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="container">
		<h2>User-based</h2>
		<p class="lead">유저 기반 추천</p>

		<div class="list-group list-store">
			<c:forEach var="store" items="${ubcfStores}" varStatus="status">
				<div class="list-group-item">
					<div class="media-left">
						<img class="media-object" src="${store.imageUrl}" onerror="this.src='<c:url value="/resources/img/main_logo.png" />'" alt="매장사진">
					</div>
					<div class="media-body">
						<h4 class="media-heading">
							<a href="<c:url value="/location/detail/${store.storeIdx }"/>">${store.name }</a>
						</h4>
						<p class="list-group-item-text">
							<a href="#" onclick="showMapStoreInfo('ubcf', ${status.index})">${store.address }</a>
						</p>
					</div>
					<div class="media-right">
						<p class="text-right evaluation">
							<c:if test="${not empty store.avgEvaluation }">
								<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
								<fmt:formatNumber value="${store.avgEvaluation }" pattern=".00"/>
							</c:if>
						</p>
						<p class="text-right"><span class="badge">${store.category.name }</span></p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<script type="text/javascript">
	var map;
	var infowindow;
	var allMarkers;
	var ibcfMarkers;
	var ubcfMarkers;
	
	function initialize() {
		infowindow = new google.maps.InfoWindow();
		
		var mapOptions = {
			zoom : 15,
			center : new google.maps.LatLng(37.59135357056928, 126.95347707605357),
			scaleControl : true,
			mapTypeId : 'roadmap'
		};
	
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
					var pos = {
						lat: position.coords.latitude,
						lng: position.coords.longitude
					};
				
					infowindow.setPosition(pos);
					infowindow.setContent('Location found.');
					map.setCenter(pos);
				}, function() {
					handleLocationError(true, infowindow, map.getCenter());
			});
		} else {
			handleLocationError(false, infowindow, map.getCenter());
		}
		
		map = new google.maps.Map(document.getElementById('map-contents'), mapOptions);
		
		ibcfMarkers = [
			<c:forEach var="store" items="${ibcfStores}" varStatus="status">
				new google.maps.Marker({
					position : new google.maps.LatLng(${store.latitude}, ${store.longitude}),
					icon: '<c:url value="/resources/img/marker1.png" />',
					title : '<c:out value="${store.name}" />',
					category : '<c:out value="${store.category.name}" />',
					map : map,
					storeIdx : '${store.storeIdx}'
				}) <c:if test="${!status.last}">, </c:if>
			</c:forEach>
		];
		
		ubcfMarkers = [
			<c:forEach var="store" items="${ubcfStores}" varStatus="status">
				new google.maps.Marker({
					position : new google.maps.LatLng(${store.latitude}, ${store.longitude}),
					icon: '<c:url value="/resources/img/marker1.png" />',
					title : '<c:out value="${store.name}" />',
					category : '<c:out value="${store.category.name}" />',
					map : map,
					storeIdx : '${store.storeIdx}'
				}) <c:if test="${!status.last}">, </c:if>
			</c:forEach>
		];
		
		allMarkers = new Array();
		allMarkers = ibcfMarkers.concat(ubcfMarkers);
		
		$.each(allMarkers, function (index, eachMarker){
			google.maps.event.addListener(eachMarker, 'click', function() {
				for (var j = 0; j < allMarkers.length; j++) {
					allMarkers[j].setIcon('<c:url value="/resources/img/marker1.png" />');
		        }
				eachMarker.setIcon('<c:url value="/resources/img/marker2.png" />');
				infowindow.close();			
				infowindow.setContent(
						'<a href="<c:url value="/location/detail"/>/' + eachMarker.storeIdx + '/"> \n'
							+ '<strong>' + eachMarker.title + '</strong> \n'
							+ '<p>' + eachMarker.category + '</p> \n'
						+ '</a>'
				);
				infowindow.open(map, eachMarker);
			});
		});
	}
	
	function handleLocationError(browserHasGeolocation, infowindow, pos) {
		infowindow.setPosition(pos);
		infowindow.setContent(browserHasGeolocation ?
			'Error: The Geolocation service failed.' :
			'Error: Your browser doesn\'t support geolocation.');
	}
	
	function showMapStoreInfo(type, markerIndex){
		if(type === 'ibcf'){
			google.maps.event.trigger(ibcfMarkers[markerIndex], 'click');
		} else if(type === 'ubcf'){
			google.maps.event.trigger(ubcfMarkers[markerIndex], 'click');
		}
	}

</script>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?key=${googleMapApiKey}&amp;callback=initialize"></script>