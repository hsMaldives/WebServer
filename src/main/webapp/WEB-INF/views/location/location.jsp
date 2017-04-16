<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="map_contents" style="width: 100%; height: 300px"></div>

<script>
	// 	//현재 위치
	// 	var currentLat = 37.558;
	// 	var currentLng = 126.9779;

	// 	//원의 반경(m)
	// 	var radi = 1000;

	/*
	 지도상의 중점을 나타낼 좌표
	 */
	var lat = 37.558;
	var lng = 126.9779;

	var map;
	var markersArray = []; //마커들이 담길 배열

	var geocoder;

	var circle;

	function initialize() {

		var haightAshbury = new google.maps.LatLng(lat, lng);
		var mapOptions = {
			zoom : 15,
			center : haightAshbury,
			scaleControl : true,
			mapTypeId : 'roadmap'
		};
		geocoder = new google.maps.Geocoder();
		map = new google.maps.Map(document.getElementById("map_contents"),
				mapOptions);

		// 		var circleOptions = {
		// 				strokeColor: "#FF0000",
		// 				strokeOpacity: 0.8,
		// 				strokeWeight: 2,
		// 				fillColor: "#FF0000",
		// 				fillOpacity: 0.35,
		// 				map: map,
		// 				center: new google.maps.LatLng(currentLat,currentLng),	//현재 위치를 적을 예정@
		// 				radius: radi //meter

		// 		};
		// 		circle = new google.maps.Circle(circleOptions);

		// Math.pow((currentLat-x),2)+Math.pow((currentLng-y),2) < Math.pow(radi) 인 x,y 좌표만 화면에 출력하자@ -> 원 내부의 점만@

		// 		google.maps.event.addListener(map,'zoom_changed',function(){
		// 			var bounds = map.getBounds();
		// 			var startPoint = bounds.getSouthWest();
		// 			var endPoint = bounds.getNorthEast();			
		// 			startPoint.lat()
		// 			startPoint.lng()
		// 			endPoint.lat()
		// 			endPoint.lng()
		// 		});

		// 		google.maps.event.addListener(map,'dragend',function(){
		// 			var bounds = map.getBounds();
		// 			var startPoint = bounds.getSouthWest();
		// 			var endPoint = bounds.getNorthEast();			
		// 			//document.write(endLo);
		// 		});

		//idle, bounds_changed

		if (markersArray.length === 0) {
			addMarker(haightAshbury);
		}

		google.maps.event
				.addListener(
						map,
						'click',
						function(e) {
							geocoder
									.geocode(
											{
												'latLng' : e.latLng
											},
											function(results, status) {
												if (status == google.maps.GeocoderStatus.OK) {
													if (results[0]) {
														if (marker) {
															marker
																	.setPosition(e.latLng);
														} else {
															marker = new google.maps.Marker(
																	{
																		position : e.latLng,
																		map : map
																	});
														}

													} else {
														document
																.getElementById('geocoding').innerHTML = 'No results found';
													}
												} else {
													document
															.getElementById('geocoding').innerHTML = 'Geocoder failed due to: '
															+ status;
												}
											});
						});

	}

	function ajaxFunc(startX, endX, startY, endY) {
		var infowindow = new google.maps.InfoWindow();
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
				if (data) {
					//만약 ajax처리를 하지 않고 배열로 테스트 할경우 아래 처리 반복문으로 처리하세요
					$.each(data, function(i, val) {
						var latLng = new google.maps.LatLng(val.latitude,
								val.longitude);
						var marker = new google.maps.Marker({
							position : latLng,
							title : val.name,
							map : map
						});
			
						google.maps.event.addListener(marker, 'click',
								function() {
									infowindow.setContent("<strong>"
											+ marker.title + "</strong>"
											+ "<p>Let's go Maldives</p>");
									infowindow.open(map, marker);
								});

						markers.push(marker);
					});
				}
				markersArray = markers;
			},
			error : function(xmlRequest) {
				alert(xmlRequest.status + " " + xmlRequest.statusText + " "
						+ xmlRequest.responseText);
			}
		});
	}

	function addMarker(location) {		

// 		google.maps.event.addListener(map, 'idle', function() {
// 			var bounds = map.getBounds();
// 			var startPoint = bounds.getSouthWest();
// 			var endPoint = bounds.getNorthEast();
// 			var startX = startPoint.lat();
// 			var startY = startPoint.lng();
// 			var endX = endPoint.lat();
// 			var endY = endPoint.lng();

// 			ajaxFunc(startX, endX, startY, endY);
// 		});
		
		google.maps.event.addListener(map, 'bounds_changed', function() {
			var bounds = map.getBounds();
			var startPoint = bounds.getSouthWest();
			var endPoint = bounds.getNorthEast();
			var startX = startPoint.lat();
			var startY = startPoint.lng();
			var endX = endPoint.lat();
			var endY = endPoint.lng();

			ajaxFunc(startX, endX, startY, endY);
		});

	}
	onload = initialize;
</script>

<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZqKafyvL0OerIRPWS_XV9GhPcWPIAL_w&callback=initmap">
	
</script>



//움직일때마다 가져오기 //범위(lim) //마커 정보 //맵 밑에 text로 리스트 -- 해당마커로 가게
