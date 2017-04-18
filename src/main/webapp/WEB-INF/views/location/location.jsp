<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="map_contents" style="width: 100%; height: 300px"></div>

<script>
	var lat = 37.558;
	var lng = 126.9779;

	var map;
	var markersArray = []; //마커들이 담길 배열

	var geocoder;

	var circle;

	function initialize() {
		infowindow = new google.maps.InfoWindow();
		var bounds;
		var startPoint;
		var endPoint;
		var startX;
		var startY;
		var endX;
		var endY;
		var location = new google.maps.LatLng(lat, lng);
		var mapOptions = {
			zoom : 15,
			center : location,
			scaleControl : true,
			mapTypeId : 'roadmap'
		};

		if (navigator.geolocation) {

			navigator.geolocation.getCurrentPosition(function(pos) {
				// 현재 위경도 값(GPS) 변수에 넣기.
				lat = pos.coords.latitude;
				lng = pos.coords.longitude;
				location = new google.maps.LatLng(lat, lng);
				mapOptions = {
					zoom : 15,
					center : location
				};
			})
		}	

		map = new google.maps.Map(document.getElementById("map_contents"),
				mapOptions);

		google.maps.event.addListener(map, 'bounds_changed', function() {
			bounds = map.getBounds();
			startPoint = bounds.getSouthWest();
			endPoint = bounds.getNorthEast();
			startX = startPoint.lat();
			startY = startPoint.lng();
			endX = endPoint.lat();
			endY = endPoint.lng();
			ajaxFunc(startX, endX, startY, endY);
		});
	}

	function ajaxFunc(startX, endX, startY, endY) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/locationsss",
			data : {
				startX : startX,
				endX : endX,
				startY : startY,
				endY : endY
			},
			dataType : "json",
			success : function(data) {
				var markers = [];
				var storeListHtml = "";
				if (data) {
					$.each(data, function(i, val) {
						var latLng = new google.maps.LatLng(val.latitude,
								val.longitude);
						var marker = new google.maps.Marker({
							position : latLng,
							title : val.name,
							map : map
						});

						google.maps.event.addListener(marker, 'click',function() {
							infowindow.close();			
							infowindow.setContent("<strong>"
									+ marker.title + "</strong>"
									+ "<p>Let's go Maldives</p>");
							infowindow.open(map, marker);
						});
						markers.push(marker);
						
						storeListHtml += '<a href="#" class="list-group-item" onclick="showMapStoreInfo(' + i + ')" data-store-idx="' + val.store_idx + '">\n';
						storeListHtml += '	<h4 class="list-group-item-heading">' + val.name + '</h4>\n';
						storeListHtml += '	<p class="list-group-item-text">' + val.address + '</p>\n';
						storeListHtml += '</a>\n';
					});
					
					$('#store-list').html(storeListHtml);
				}
				markersArray = markers;
			},
			error : function(xmlRequest) {
				alert(xmlRequest.status + " " + xmlRequest.statusText + " "
						+ xmlRequest.responseText);
			}
		});
	}
	
	function showMapStoreInfo(markerIndex){
		google.maps.event.trigger(markersArray[markerIndex], 'click');
	}
</script>

<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZqKafyvL0OerIRPWS_XV9GhPcWPIAL_w&callback=initialize">
	
</script>

<div class="row">
	<div class="col-xs-12">
		<div id="store-list" class="list-group">
		</div>
	</div>
</div>