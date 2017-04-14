<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style>
#map {
	width: 100%;
	height: 400px;
	d background-color: grey;
}
</style>
</head>
<body>
	<h3>My Google Maps Demo</h3>
	<div id="map"></div>


	<script>
		var map;

		function loadResults(data) {
			var items, markers_data = [];
			if (data.venues.length > 0) {
				items = data.venues;

				for (var i = 0; i < items.length; i++) {
					var item = items[i];

					if (item.location.lat != undefined
							&& item.location.lng != undefined) {
						var icon = 'https://foursquare.com/img/categories/food/default.png';

						markers_data.push({
							lat : item.location.lat,
							lng : item.location.lng,
							title : item.name,
							icon : {
								size : new google.maps.Size(32, 32),
								url : icon
							}
						});
					}
				}
			}

			map.addMarkers(markers_data);
		}

		function printResults(data) {
			$('#foursquare-results').text(JSON.stringify(data));
			prettyPrint();
		}

		$(document).on('click', '.pan-to-marker', function(e) {
			e.preventDefault();

			var position, lat, lng, $index;

			$index = $(this).data('marker-index');

			position = map.markers[$index].getPosition();

			lat = position.lat();
			lng = position.lng();

			map.setCenter(lat, lng);
		});

		$(document)
				.ready(
						function() {
							prettyPrint();
							map = new GMaps({
								div : '#map',
								lat : -12.043333,
								lng : -77.028333
							});

							map.on('marker_added', function(marker) {
								var index = map.markers.indexOf(marker);
								$('#results').append(
										'<li><a href="#" class="pan-to-marker" data-marker-index="' + index + '">'
												+ marker.title + '</a></li>');

								if (index == map.markers.length - 1) {
									map.fitZoom();
								}
							});

							var xhr = $
									.getJSON('https://coffeemaker.herokuapp.com/foursquare.json?q[near]=Lima,%20PE&q[query]=Ceviche');

							xhr.done(printResults);
							xhr.done(loadResults);
						});
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAERjPrIt4_HEpa5FJY70bvbi9D3Ep3OCo&callback=initMap">
		
	</script>
</body>
</html>