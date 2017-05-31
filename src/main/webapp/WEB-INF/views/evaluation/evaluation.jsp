<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/resources/css/star-rating.css" />" media="all" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/themes/krajee-svg/theme.css" />" media="all" rel="stylesheet" type="text/css" />
<script src="<c:url value="/resources/js/star-rating.js" />"></script>
<script src="<c:url value="/resources/themes/krajee-svg/theme.js"/>"></script>
<script src="<c:url value="/resources/js/locales/LANG.js" />"></script>

<div class="container">
	<h2>장소 평가</h2>
	<p>기존에 방문했던 장소를 평가할 수 있습니다.</p>

	<div class="map" id="map" style="height: 400px;"></div>
	
	<div class="container">
		<form id="rating-form" action="<c:url value="/api/rating/storeAndRatingInfo" />">
			<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label">장소명:</label>
					<p id="place-name"></p>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label" for="eva1">
						가격:</label>
					<input type="text" name="eva1" class="rating rating-loading eva" data-min="0" data-max="5" data-step="0.5"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label" for="eva2">
						맛:</label>
					<input type="text" name="eva2" class="rating rating-loading eva" data-min="0" data-max="5" data-step="0.5"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label" for="eva3">
						위생:</label>
					<input type="text" name="eva3" class="rating rating-loading eva" data-min="0" data-max="5" data-step="0.5"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label" for="eva4">
						친절:</label>
					<input type="text" name="eva4" class="rating rating-loading eva" data-min="0" data-max="5" data-step="0.5"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label" for="eva5">
						양:</label>
					<input type="text" name="eva5" class="rating rating-loading eva" data-min="0" data-max="5" data-step="0.5"/>
				</div>
			</div>
			
			<button type="submit">평가</button>
		</form>
	</div>

	<script type="text/javascript"
		src="//apis.daum.net/maps/maps3.js?apikey=3b25e914a09ca9a3be537c07aa4f7eff&amp;libraries=services"></script>
	<script>
		// 마커를 담을 배열입니다
		var selectedPlace;

		var placeOverlay = new daum.maps.CustomOverlay({zIndex:1}), 
		    contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다 
		    markers = [], // 마커를 담을 배열입니다
		    currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다
		 
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		        level: 5 // 지도의 확대 레벨
		    };  

		// 지도를 생성합니다    
		var map = new daum.maps.Map(mapContainer, mapOption);

		// 장소 검색 객체를 생성합니다
		var ps = new daum.maps.services.Places(map);
		
		
		// 지도에 idle 이벤트를 등록합니다
		daum.maps.event.addListener(map, 'idle', searchPlaces);

		// 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다 
		contentNode.className = 'placeinfo_wrap';

		// 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
		// 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 daum.maps.event.preventMap 메소드를 등록합니다 
		addEventHandle(contentNode, 'mousedown', daum.maps.event.preventMap);
		addEventHandle(contentNode, 'touchstart', daum.maps.event.preventMap);

		// 커스텀 오버레이 컨텐츠를 설정합니다
		placeOverlay.setContent(contentNode);  

		// 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
		function addEventHandle(target, type, callback) {
		    if (target.addEventListener) {
		        target.addEventListener(type, callback);
		    } else {
		        target.attachEvent('on' + type, callback);
		    }
		}
		
		// 키워드 검색을 요청하는 함수입니다
		function searchPlaces() {

			// 커스텀 오버레이를 숨깁니다 
			// 	    placeOverlay.setMap(null);

			// 지도에 표시되고 있는 마커를 제거합니다
			removeMarker();

			ps.categorySearch("FD6", placesSearchCB, {
				useMapBounds : true
			});
		}

		// 키워드로 장소를 검색합니다
// 		searchPlaces();
		
		daum.maps.event.addListener(map, 'idle', searchPlaces);


		// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
		function placesSearchCB(status, data, pagination) {
			if (status === daum.maps.services.Status.OK) {

				// 정상적으로 검색이 완료됐으면
				// 검색 목록과 마커를 표출합니다
				displayPlaces(data.places);


			} else if (status === daum.maps.services.Status.ZERO_RESULT) {

				alert('검색 결과가 존재하지 않습니다.');
				return;

			} else if (status === daum.maps.services.Status.ERROR) {

				alert('검색 결과 중 오류가 발생했습니다.');
				return;

			}
		}

		// 검색 결과 목록과 마커를 표출하는 함수입니다
		function displayPlaces(places) {
		
		    // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
		    // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
		
		    for ( var i=0; i<places.length; i++ ) {
		
		            // 마커를 생성하고 지도에 표시합니다
		            var marker = addMarker(new daum.maps.LatLng(places[i].latitude, places[i].longitude));
		
		            // 마커와 검색결과 항목을 클릭 했을 때
		            // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
		            (function(marker, place) {
		                daum.maps.event.addListener(marker, 'click', function() {
		                    displayPlaceInfo(place);
		                });
		            })(marker, places[i]);
		    }
		}

		// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
		function addMarker(position) {
		    var imageSrc = '<c:url value="/resources/img/marker1.png" />', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		        imageSize = new daum.maps.Size(43, 43),  // 마커 이미지의 크기
		        imgOptions =  {},
		        markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imgOptions),
		            marker = new daum.maps.Marker({
		            position: position, // 마커의 위치
		            image: markerImage 
		        });
		
		    marker.setMap(map); // 지도 위에 마커를 표출합니다
		    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
		
		    return marker;
		}

		// 지도 위에 표시되고 있는 마커를 모두 제거합니다
		function removeMarker() {
			for (var i = 0; i < markers.length; i++) {
				markers[i].setMap(null);
			}
			markers = [];
		}

		function displayPlaceInfo (place) {
			selectedPlace = place;
			
			$('#place-name').text(place.title);
			
		    var content = '<div class="placeinfo">' +
		                    '   <a class="title" href="' + place.placeUrl + '" target="_blank" title="' + place.title + '">' + place.title + '</a>';   
		
		    if (place.newAddress) {
		        content += '    <span title="' + place.newAddress + '">' + place.newAddress + '</span>' +
		                    '  <span class="jibun" title="' + place.address + '">(지번 : ' + place.address + ')</span>';
		    }  else {
		        content += '    <span title="' + place.address + '">' + place.address + '</span>';
		    }                
		   
		    content += '    <span class="tel">' + place.phone + '</span>' + 
		                '</div>' + 
		                '<div class="after"></div>';
		
		    contentNode.innerHTML = content;
		    placeOverlay.setPosition(new daum.maps.LatLng(place.latitude, place.longitude));
		    placeOverlay.setMap(map);  
		}
		
		$(function (){
			$('#rating-form').submit(function (event){
				event.preventDefault();
				
				if(typeof(selectedPlace) === 'undefined'){
					alert('선택된 장소가 없습니다');
					return;
				}
				
				var sendObject = new Object();
				var ratings = [];
				$.each($('.eva'), function (index, each) {
					ratings.push($(each).val());
					
				});
				
				sendObject.storeInfo = selectedPlace;
				sendObject.rating = ratings;
				
				$.ajax({
					url: $('#rating-form').attr('action'),
					method: 'POST',
					data: JSON.stringify(sendObject),
					contentType: 'application/json',
					success: function (data){
						console.log(data);
						alert("평가가 완료되었습니다.");
						
						selectedPlace = undefined;
						
						$('#place-name').text('');
						
						$.each($('.eva'), function (index, each){
							$(each).rating('reset');
						});
					},
					error: function (jqXHR, textStatus, errorThrown){
						alert("오류가 발생하였습니다.");
					}
				});
			});
			
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
						map.setCenter(position.coords.latitude, position.coords.longitude);
					}, function() {
				});
			} else {}
		});
	</script>
</div>