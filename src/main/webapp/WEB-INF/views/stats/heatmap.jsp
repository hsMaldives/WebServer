<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="<c:url value="/resources/js/jquery-ui-1.12.1/jquery-ui.min.js"/>"></script>

<link href="<c:url value="/resources/js/jquery-ui-1.12.1/jquery-ui.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/js/jquery-ui-1.12.1/jquery-ui.theme.min.css"/>" rel="stylesheet">

<div class="row">
	<div class="col-xs-12">
		<div id="map-contents" style="width: 100%; height: 400px"></div>
	</div>
</div>
<div class="row margin-top-20">
	<div class="container panel panel-default">
		<div class="row panel-body">
			<div class="col-xs-12">
				<form id="layer-setting-form">
					<div class="form-group row">
						<label for="category" class="col-sm-2 control-label">유형</label>
						<div class="col-sm-10 col-lg-6">
							<select name="category" class="form-control">
								<option value="">전체</option>
								<c:forEach var="category" items="${categorys}">
									<option value="${category.categoryCode}">${category.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				
					<div class="form-group row">
						<label class="col-sm-2 control-label">평점</label>
						<div class="col-sm-10 col-lg-6">
							<div id="eva-slider-range"></div>
							<p id="eva-range">0 ~ 5</p>
							<input type="hidden" name="begin-eva"  value="0" />
							<input type="hidden" name="end-eva" value="5" />
						</div>
					</div>
			
					<div class="form-group row">
						<label class="col-sm-2 control-label">나이</label>
						<div class="col-sm-10 col-lg-6">
							<div id="age-slider-range"></div>
							<p id="age-range">0 ~ 100</p>
							<input type="hidden" name="begin-age" value="0" />
							<input type="hidden" name="end-age" value="100" />
						</div>
					</div>
			
					<div class="form-group row">
						<label for="category" class="col-sm-2 control-label">직업</label>
						<div class="col-sm-10 col-lg-6">
							<select name="job-idx" class="form-control">
								<option value="">전체</option>
								<c:forEach var="job" items="${jobs}">
									<option value="${job.jobIdx}">${job.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
	
					<div class="form-group row">
						<label class="col-sm-2 control-label">성별</label>
						<div class="col-sm-10 col-lg-6">
							<label class="radio-inline">
								<input type="radio" name="sex" value="" checked="checked" /> 전체
							</label>
							<label class="radio-inline">
								<input type="radio" name="sex" value="0" /> 남
							</label> 
							<label class="radio-inline">
								<input type="radio" name="sex" value="1" /> 여
							</label>
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 control-label">기간</label>
						<div class="col-sm-10 col-lg-6">
							<div class="col-sm-5">
								<input type="date" name="begin-date" class="form-control" />
							</div>
							<p class="col-sm-2 text-center"> ~ </p>
							<div class="col-sm-5">
								<input type="date" name="end-date" class="form-control" />
							</div>
						</div>
					</div>
			
					<button type="submit" class="btn btn-default">적용</button>
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?key=${googleMapApiKey}&amp;v=3&amp;libraries=visualization"></script>

<script type="text/javascript">
	function ftOnLoadClientApi() {
		gapi.client.setApiKey('AIzaSyCZqKafyvL0OerIRPWS_XV9GhPcWPIAL_w');
	}
</script>

<script type="text/javascript" src="https://apis.google.com/js/client.js?onload=ftOnLoadClientApi"></script>

<script type="text/javascript">
	var map;
	var heatmap;
	var infowindow;
	var markersArray;
	var selectedMarker;

	function loadApi() {
		gapi.client.load('fusiontables', 'v1', initialize);
	}

	function initialize() {
		var mapDiv = document.getElementById('map-contents');
		infowindow = new google.maps.InfoWindow();

		map = new google.maps.Map(mapDiv, {
			center : new google.maps.LatLng(37.59135357056928,
					126.95347707605357),
			zoom : 11
		});
		
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
		
		var query = 'SELECT \'Location\', \'AvgEva\' '
				+ 'FROM ${fusionTableId} '
				+ 'ORDER BY Date ' + 'LIMIT 1000 ';
		var request = gapi.client.fusiontables.query.sqlGet({
			sql : query
		});
		request.execute(function(response) {
			onDataFetched(response);
		});
		
		google.maps.event.addListener(map, 'bounds_changed', function() {
			storeListLoad();
		});
	}
	
	function handleLocationError(browserHasGeolocation, infowindow, pos) {
		infowindow.setPosition(pos);
		infowindow.setContent(browserHasGeolocation ?
			'Error: The Geolocation service failed.' :
			'Error: Your browser doesn\'t support geolocation.');
	}
	
	function storeListLoad(){
		bounds = map.getBounds();
		startPoint = bounds.getSouthWest();
		endPoint = bounds.getNorthEast();
		startX = startPoint.lat();
		startY = startPoint.lng();
		endX = endPoint.lat();
		endY = endPoint.lng();
		if(map.getZoom() > 15){
			ajaxFunc(startX, endX, startY, endY, $('#layer-setting-form [name="category"]').val());
		} else {
			removeMarker()
		}
	}
	
	function ajaxFunc(startX, endX, startY, endY, categoryCode) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/location/getStores",
			data : {
				startX : startX,
				endX : endX,
				startY : startY,
				endY : endY,
				categoryCode: categoryCode
			},
			dataType : "json",
			success : function(data) {
				removeMarker();
				var markers = [];
				var storeListHtml = "";
				if (data) {
					$.each(data, function(i, val) {
						var latLng = new google.maps.LatLng(val.latitude, val.longitude);
						if(typeof(selectedMaker) === 'undefined'
								|| selectedMaker.storeIdx !== val.storeIdx ){
							var marker = new google.maps.Marker({
								position : latLng,
								icon: '<c:url value="/resources/img/marker1.png" />',
								title : val.name,
								category : val.category.name,					
								map : map,
								storeIdx : val.storeIdx
							});
	
							google.maps.event.addListener(marker, 'click', function() {
								if(typeof(selectedMaker) !== 'undefined'){
									markersArray.push(selectedMaker);
								}
								selectedMaker = marker;
								
								for (var j = 0; j < markers.length; j++) {
						          markers[j].setIcon('<c:url value="/resources/img/marker1.png" />');
						        }
								marker.setIcon('<c:url value="/resources/img/marker2.png" />');
								infowindow.close();			
								infowindow.setContent(
										'<a href="<c:url value="/location/detail/"/>' + marker.storeIdx + '"> \n'
											+ '<strong>' + marker.title + '</strong> \n'
											+ '<p>' + marker.category + '</p> \n'
										+ '</a>'
								);
								infowindow.open(map, marker);
							});
							markers.push(marker);
						}
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
	
	function removeMarker(){
		if(typeof(markersArray) !== 'undefined'){
			for (var i = 0; i < markersArray.length; i++) {
				if(typeof(selectedMaker) === 'undefined' || selectedMaker !== markersArray[i]){
					markersArray[i].setMap(null);
				}
	        }
		}
		
		delete markersArray;
	}

	function onDataFetched(response) {
		if (response.error) {
			alert('Unable to fetch data. ' + response.error.message + ' ('
					+ response.error.code + ')');
		} else {
			drawHeatmap(extractLocations(response.rows));
		}
	}

	function extractLocations(rows) {
		var locations = [];
		if(typeof(rows) !== 'undefined'){
			for (var i = 0; i < rows.length; ++i) {
				var row = rows[i];
				if (row[0]) {
					var locationSplit = row[0].split(', ');
					locations[i] = new google.maps.LatLng(locationSplit[0],
							locationSplit[1]);
				}
			}
		}
		return locations;
	}

	function drawHeatmap(locations) {
		if (typeof (heatmap) !== 'undefined') {
			heatmap.setMap(null);
		}

		heatmap = new google.maps.visualization.HeatmapLayer(
				{
					dissipating : true,
					gradient : [ 'rgba(102,255,0,0)', 'rgba(147,255,0,1)',
							'rgba(193,255,0,1)', 'rgba(238,255,0,1)',
							'rgba(244,227,0,1)', 'rgba(244,227,0,1)',
							'rgba(249,198,0,1)', 'rgba(255,170,0,1)',
							'rgba(255,113,0,1)', 'rgba(255,57,0,1)',
							'rgba(255,0,0,1)' ],
					opacity : 0.84,
					radius : 16,
					data : locations
				});
		heatmap.setMap(map);
	}

	google.maps.event.addDomListener(window, 'load', loadApi);
	
	$(function (){
		$('#layer-setting-form').submit(function(event) {
			var whereQuery = '';

			whereQuery += 'AvgEva >= '
					+ $('#layer-setting-form [name="begin-eva"]').val()
					+ ' AND ' + 'AvgEva <= '
					+ $('#layer-setting-form [name="end-eva"]').val()
					+ '\n';
			whereQuery += 'AND ' + 'Age >= '
					+ $('#layer-setting-form [name="begin-age"]').val()
					+ ' AND ' + 'Age <= '
					+ $('#layer-setting-form [name="end-age"]').val()
					+ '\n';
			whereQuery += 'AND ' + 'Category like \''
					+ $('#layer-setting-form [name="category"]').val()
					+ '%\'' + '\n';
			if($('#layer-setting-form [name="job-idx"]').val() !== ''){
				whereQuery += 'AND ' + 'JobIdx = \''
						+ $('#layer-setting-form [name="job-idx"]').val()
						+ '\'' + '\n';
			}
			
			if($('#layer-setting-form [name="sex"]').val() !== ''){
				whereQuery += 'AND ' + 'Sex = '
						+ $('#layer-setting-form [name="sex"]').val()
						+ '\n';
			}
			
			if($('#layer-setting-form [name="begin-date"]').val() !== ''){
				whereQuery += 'AND ' + 'Date >= \''
					+ $('#layer-setting-form [name="begin-date"]').val() + "\' \n"
			}
			
			if($('#layer-setting-form [name="end-date"]').val() !== ''){
				whereQuery += ' AND ' + 'Date <= \''
				+ $('#layer-setting-form [name="end-date"]').val() + '\' \n';
			}
			
			var query = 'SELECT \'Location\', \'AvgEva\' \n'
				+ 'FROM 1_hzEAwwva6MTOdWw6gN-uU7L2ikjeW5tnGqXWj-P \n'
				+ 'WHERE ' + whereQuery
				+ 'ORDER BY Date ' + 'LIMIT 1000 \n';
			
			console.log(query);
			
			var request = gapi.client.fusiontables.query.sqlGet({
				sql : query
			});
			request.execute(function(response) {
				onDataFetched(response);
			});
			
			storeListLoad();
			
			event.preventDefault();
		});
	});
</script>

<script>
	$(function() {
		$('#eva-slider-range').slider({
			range : true,
			min : 0,
			max : 5,
			step : 0.5,
			values : [ 0, 5 ],
			slide : function(event, ui) {
				$('[name="begin-eva"]').val(ui.values[0]);
				$('[name="end-eva"]').val(ui.values[1]);
				$('#eva-range').text(ui.values[0] + " ~ " + ui.values[1]);
			}
		});

		$('#age-slider-range').slider({
			range : true,
			min : 0,
			max : 100,
			step : 1,
			values : [ 1, 100 ],
			slide : function(event, ui) {
				$('[name="begin-age"]').val(ui.values[0]);
				$('[name="end-age"]').val(ui.values[1]);
				$('#age-range').text(ui.values[0] + " ~ " + ui.values[1]);
			}
		});
		
		$('#layer-setting-form [name="begin-date"]').change(function (){
			$('[name="end-date"]').attr('min', $('[name="begin-date"]').val());
			
			if($('[name="end-date"]').val() !== '' 
					&& $('[name="begin-date"]').val() > $('[name="end-date"]').val()){
				$('[name="end-date"]').val($('[name="begin-date"]').val());
			}
		});
		
		$('#layer-setting-form [name="end-date"]').change(function (){
			$('[name="begin-date"]').attr('max', $('[name="end-date"]').val());
			
			if($('[name="begin-date"]').val() !== '' 
					&& $('[name="begin-date"]').val() > $('[name="end-date"]').val()){
				$('[name="begin-date"]').val($('[name="end-date"]').val());
			}
		});
	});
</script>