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
	
	var infowindow;

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
	
	function handleLocationError(browserHasGeolocation, infowindow, pos) {
		infowindow.setPosition(pos);
		infowindow.setContent(browserHasGeolocation ?
			'Error: The Geolocation service failed.' :
			'Error: Your browser doesn\'t support geolocation.');
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
				var markers = [];
				var storeListHtml = "";
				if (data) {
					$.each(data, function(i, val) {
						var latLng = new google.maps.LatLng(val.latitude,
								val.longitude);
						var marker = new google.maps.Marker({
							position : latLng,
							icon: '<c:url value="/resources/img/marker1.png" />',
							title : val.name,
							category : val.category.name,							
							map : map
						});

						google.maps.event.addListener(marker, 'click', function() {
							for (var j = 0; j < markers.length; j++) {
					          markers[j].setIcon('<c:url value="/resources/img/marker1.png" />');
					        }
							marker.setIcon('<c:url value="/resources/img/marker2.png" />');
							infowindow.close();			
							infowindow.setContent("<strong>"
									+ marker.title + "</strong>"
									+ "<p>"+marker.category+"</p>");
							infowindow.open(map, marker);
						});
						markers.push(marker);
						
// 						storeListHtml += '<a href=<c:url value="/location/detail"/>?store_idx='+ val.storeIdx +' class="list-group-item" onclick="showMapStoreInfo(' + i + ')" data-store-idx="' + val.storeIdx + '">';
// 						storeListHtml += '	<h4 class="list-group-item-heading">' + val.name + ' ';
// 						storeListHtml += '[' + val.category.name + ']</h4>';
// 						storeListHtml += '	<p class="list-group-item-text">' + val.address + '</p>';
// 						storeListHtml += '</a>\n';
						
						
						storeListHtml += '<a href="<c:url value="/location/detail"/>?store_idx='+ val.storeIdx +'" class="" onclick="showMapStoreInfo(' + i + ')" data-store-idx="' + val.storeIdx + '">';
// 						storeListHtml +='<div class="container">';
						storeListHtml +='<div class="row">';

						storeListHtml +='<section class="content">';
								
						storeListHtml +=	'<div class="col-md-8 col-md-offset-2">';
						storeListHtml +=	'<div class="panel panel-default">';
						storeListHtml +=		'<div class="panel-body">'	;					
											
						storeListHtml +=				'<div class="table-container">';
						storeListHtml +=		'<table class="table table-filter">';
						storeListHtml +=			'<tbody>';
						storeListHtml +=				'<tr>';							
						storeListHtml +=					'<td>';
						storeListHtml +=						'<div class="media">';
						storeListHtml +=							'<a href="#" class="pull-left">';
						
						storeListHtml +=							'</a>';
						storeListHtml +=							'<div class="media-body">';
						storeListHtml +=								'<span class="media-meta pull-right">('+val.category.name+')</span>';
						storeListHtml +=								'<h4 class="title">'+val.name +' ';
																			
						storeListHtml +=								'</h4>';
						storeListHtml +=								'<span class="summary">'+val.address+'</span>'+'<span class=pull-right><img src="'+val.imageUrl+'" onerror="this.src=\'<c:url value="/resources/img/main_logo.png" />\'" style="width:50px; height:50px;"></span>';
						storeListHtml +=							'</div>';
						storeListHtml +=						'</div>';
						storeListHtml +=					'</td>';
						storeListHtml +=				'</tr>';
														
						storeListHtml +=			'</tbody>';
						storeListHtml +=		'</table>';
						storeListHtml +=	'</div>';
						storeListHtml +='</div>';
						storeListHtml +='</div>';
									
						storeListHtml +='</div>';
						storeListHtml +='</section>';
							
// 						storeListHtml +='</div>';
						storeListHtml +='</div>';
						storeListHtml += '</a>';
						
						
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
	src="https://maps.googleapis.com/maps/api/js?key=${googleMapApiKey}&amp;callback=initialize">
</script>

<div class="row">
	<div class="col-xs-12">
		<div id="store-list" class="list-group">
		</div>
	</div>
	
	
	
</div>
	
	