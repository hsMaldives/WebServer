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

<div class="container container-wrapper margin-top-20">
	<div class="row">
		<ul id="eval-tab-list" class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#ubcf" aria-controls="profile" role="tab" data-toggle="tab">사용자 기반</a></li>
		    <li role="presentation"><a href="#ibcf" aria-controls="home" role="tab" data-toggle="tab">아이템 기반</a></li>
		    <li role="presentation"><a href="#local" role="tab" data-toggle="tab">위치 기반</a>
	    </ul>
		
		<div class="tab-content">
			<div class="tab-pane active" id="ubcf">
				<h2>User-based</h2>
				<p class="lead">유저 기반 추천</p>
		
				<div class="list-group list-store">
					<c:forEach var="store" items="${ubcfStores}" varStatus="status">
						<div class="list-group-item" data-latitude="${store.latitude}" data-longitude="${store.longitude}">
							<div class="media-left">
								<div class="img-container">
									<img class="media-object" src="${store.imageUrl}" onerror="this.src='<c:url value="/resources/img/main_logo.png" />'" alt="매장사진">
								</div>
							</div>
							<div class="media-body">
								<h4 class="media-heading">
									<a href="<c:url value="/location/detail/${store.storeIdx }"/>">${store.name }</a>
								</h4>
								<p class="list-group-item-text">
									<a href="#" onclick="showMapStoreInfo('ubcf', ${status.index})">${store.address }</a>
									(<span class="distance"></span>km)
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
			
			<div class="tab-pane" id="ibcf">
				<h2>Item-based</h2>
				<p class="lead">아이템 기반 추천</p>
		
				<div class="list-group list-store">
					<c:forEach var="store" items="${ibcfStores}" varStatus="status">
						<div class="list-group-item" data-latitude="${store.latitude}" data-longitude="${store.longitude}">
							<div class="media-left">
								<div class="img-container">
									<img class="media-object" src="${store.imageUrl}" onerror="this.src='<c:url value="/resources/img/main_logo.png" />'" alt="매장사진">						
								</div>
							</div>
							<div class="media-body">
								<h4 class="media-heading">
									<a href="<c:url value="/location/detail/${store.storeIdx }"/>">${store.name }</a>
								</h4>
								<p class="list-group-item-text">
									<a href="#" onclick="showMapStoreInfo('ibcf', ${status.index})">${store.address }</a>
									(<span class="distance"></span>km)
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
			<div class="tab-pane" id="local">
				<h2>Location-based</h2>
				<p class="lead">위치 기반 추천</p>
				
				<div class="list-group list-store">
					
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var map;
	var infowindow;
	var locationMarkers = new Array();
	var ibcfMarkers;
	var ubcfMarkers;
	var selectedMarkerStoreIdx;
	
	var numberFormat = new Intl.NumberFormat({ minimumFractionDigits: 3 });
	var evalFormat = new Intl.NumberFormat({ minimumFractionDigits: 2 });
	
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
		
		$.each(getAllMarkers(), function (index, eachMarker){
			google.maps.event.addListener(eachMarker, 'click', function() {
				var allMarkers = getAllMarkers();
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
				selectedMarkerStoreIdx = eachMarker.storeIdx;
			});
		});
		
		google.maps.event.addListener(map, 'idle', function() {			
			var bounds = map.getBounds();
			var startPoint = bounds.getSouthWest();
			var endPoint = bounds.getNorthEast();
			startX = startPoint.lat();
			startY = startPoint.lng();
			endX = endPoint.lat();
			endY = endPoint.lng();
			
			removeMarker(locationMarkers);
			ajaxFunc(startX, endX, startY, endY);
			
			$.each($('.list-store .list-group-item'), function (index, each){
				var lat1 = map.getCenter().lat()
				var lng1 = map.getCenter().lng()
				var lat2 = $(each).attr("data-latitude")
				var lng2 = $(each).attr("data-longitude")
				
				$(each).find('.distance').text(numberFormat.format(calDistance(lat1, lng1, lat2, lng2)));
				
			});
			
			ibcfListOrderByDistance();
		});
	}
	
	function removeMarker(markers){
		$.each(markers, function (inedx, each){
			each.setMap(null);
		});
	}
	
	function ajaxFunc(startX, endX, startY, endY) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/location/getStores",
			data : {
				startX : startX,
				endX : endX,
				startY : startY,
				endY : endY
			},
			dataType : "json",
			success : function(data) {
				var mapCenterLat = map.getCenter().lat()
				var mapCenterLng = map.getCenter().lng()
				var markers = [];
				var storeListHtml = "";
				
				if (data) {
					var markers;
					$.each(data, function(i, val) {
						var latLng = new google.maps.LatLng(val.latitude,
								val.longitude);
						var marker = new google.maps.Marker({
							position : latLng,
							icon: '<c:url value="/resources/img/marker1.png" />',
							title : val.name,
							category : val.category.name,	
							storeIdx : val.storeIdx,
							map : map
						});
						
						google.maps.event.addListener(marker, 'click', function() {
							var allMarkers = getAllMarkers();
							
							for (var j = 0; j < allMarkers.length; j++) {
								allMarkers[j].setIcon('<c:url value="/resources/img/marker1.png" />');
					        }
							marker.setIcon('<c:url value="/resources/img/marker2.png" />');
							infowindow.close();			
							infowindow.setContent(
									'<a href="<c:url value="/location/detail"/>/' + marker.storeIdx + '/"> \n'
										+ '<strong>' + marker.title + '</strong> \n'
										+ '<p>' + marker.category + '</p> \n'
									+ '</a>'
							);
							infowindow.open(map, marker);
						});
						markers.push(marker);

						storeListHtml += '<div class="list-group-item" data-latitude="' + val.latitude + '" data-longitude="' + val.longitude + '">\n';
						storeListHtml += '	<div class="media-left">\n';
						storeListHtml += '		<div class="img-container">\n';
						storeListHtml += '			<img class="media-object" src="' + val.imageUrl + '" onerror="this.src=\'<c:url value="/resources/img/main_logo.png" />\'" alt="매장사진">\n';					
						storeListHtml += '		</div>\n';
						storeListHtml += '	</div>\n';
						storeListHtml += '	<div class="media-body">\n';
						storeListHtml += '		<h4 class="media-heading">\n';
						storeListHtml += '			<a href="<c:url value="/location/detail/"/>' + val.storeIdx + '">' + val.name + '</a>\n';
						storeListHtml += '		</h4>\n';
						storeListHtml += '		<p class="list-group-item-text">\n';
						storeListHtml += '			<a href="#" onclick="showMapStoreInfo(\'local\', ' + i + ')">' + val.address + '</a>\n';
						storeListHtml += '			(<span class="distance">' + numberFormat.format(calDistance(mapCenterLat, mapCenterLng, val.latitude, val.longitude)) + '</span>km)\n';
						storeListHtml += '		</p>\n';
						storeListHtml += '	</div>\n';
						storeListHtml += '	<div class="media-right">\n';
						storeListHtml += '		<p class="text-right evaluation">\n';
						
						if(typeof(val.avgEvaluation) !== 'undefined'){
							storeListHtml += '				<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>\n';
							storeListHtml += '				' + evalFormat.format(val.avgEvaluation) + '\n';
						}
						
						storeListHtml += '		</p>\n';
						storeListHtml += '		<p class="text-right"><span class="badge">' + val.category.name + '</span></p>\n';
						storeListHtml += '	</div>\n';
						storeListHtml += '</div>\n';

					});
					
					$('#local .list-store').html(storeListHtml);
				}
				
				locationMarkers = markers;
			},
			error : function(xmlRequest) {
				alert(xmlRequest.status + " " + xmlRequest.statusText + " "
						+ xmlRequest.responseText);
			}
		});
	}

	
	function calDistance(lat1, lng1, lat2, lng2){  
	    var theta, dist;
	    
	    theta = lng1 - lng2;  
	    dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))   
	          * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));  
	    dist = Math.acos(dist);  
	    dist = rad2deg(dist);  
	      
	    dist = dist * 60 * 1.1515;   
	    dist = dist * 1.609344;    // 단위 mile 에서 km 변환.
	  
	    return dist;  
	}  
	
	function deg2rad(deg){  
	    return (deg * Math.PI / 180);  
	}  
	  
	// 주어진 라디언(radian) 값을 도(degree) 값으로 변환  
	function rad2deg(rad){  
	    return (rad * 180 / Math.PI);  
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
		} else if(type === 'local'){
			google.maps.event.trigger(locationMarkers[markerIndex], 'click');
		}
	}
	
	function getAllMarkers(){
		return ibcfMarkers.concat(ubcfMarkers).concat(locationMarkers);
	}
	
	function ibcfListOrderByDistance(){
		var sort_by_distance = function(a, b) {
			return $(a).find('.distance').text().localeCompare($(b).find('.distance').text());
		}
		var list = $("#ibcf .list-store > div.list-group-item").get();
		
		list.sort(sort_by_distance);
		for (var i = 0; i < list.length; i++) {
			list[i].parentNode.appendChild(list[i]);
		}
	}
</script>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?key=${googleMapApiKey}&amp;callback=initialize"></script>