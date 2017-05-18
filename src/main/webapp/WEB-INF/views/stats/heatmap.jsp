<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="<c:url value="/resources/js/jquery-ui-1.12.1/jquery-ui.min.js"/>"></script>

<link href="<c:url value="/resources/js/jquery-ui-1.12.1/jquery-ui.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/js/jquery-ui-1.12.1/jquery-ui.theme.min.css"/>" rel="stylesheet">

<script>
	function initialize() {
		map = new google.maps.Map(document.getElementById('map_contents'), {
			center : {
				lat : 37.58365440060275,
				lng : 127.0109683705358
			},
			zoom : 11
		});

		layer = new google.maps.FusionTablesLayer({
			query : {
				select : '\'Location\', \'AvgEva\'',
				from : '${fusionTableId}'
			},
			heatmap : {
				enabled : true
			}
		});

		layer.setMap(map);
	}
</script>

<script>
	$(function() {
		$('#eva-slider-range').slider({
			range : true,
			min: 0,
			max: 5,
			step: 0.5,
			values : [ 0, 5 ],
			slide : function(event, ui) {
				$('[name="begin-eva"]').val(ui.values[0]);
				$('[name="end-eva"]').val(ui.values[1]);
				$('#eva-range').text(ui.values[0] + " ~ " + ui.values[1]);
			}
		});
		
		$('#age-slider-range').slider({
			range : true,
			min: 0,
			max: 100,
			step: 1,
			values : [ 1, 100 ],
			slide : function(event, ui) {
				$('[name="begin-age"]').val(ui.values[0]);
				$('[name="end-age"]').val(ui.values[1]);
				$('#age-range').text(ui.values[0] + " ~ " + ui.values[1]);
			}
		});
		
		$('#layer-setting-form').submit(function(event){
			layer.setMap(null);
			
			var whereQuery = '';
			
			whereQuery += 'AvgEva >= ' + $('#layer-setting-form [name="begin-eva"]').val() 
							+ ' AND ' + 'AvgEva <= ' + $('#layer-setting-form [name="end-eva"]').val() + '\n';
			whereQuery += 'AND ' + 'Age >= ' + $('#layer-setting-form [name="begin-age"]').val() 
							+ ' AND ' + 'Age <= ' + $('#layer-setting-form [name="end-age"]').val() + '\n';
			whereQuery += 'AND ' + 'Category like \'' + $('#layer-setting-form [name="category"]').val() + '%\'' + '\n';
			
			whereQuery += 'AND ' + 'Sex = ' + $('#layer-setting-form [name="sex"]').val() + '\n';
		
			
			console.log(whereQuery);
			
			layer = new google.maps.FusionTablesLayer({
				query : {
					select : '\'Location\'',
					from : '${fusionTableId}',
					where : whereQuery
				},
				heatmap : {
					enabled : true
				}
			});
			
			layer.setMap(map);
			
			event.preventDefault();
		});
	});
</script>

<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZqKafyvL0OerIRPWS_XV9GhPcWPIAL_w&callback=initialize">
	
</script>

<div class="row">
	<div class="col-xs-12">
		<div id="map_contents" style="width: 100%; height: 400px"></div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<form id="layer-setting-form">
			<div>
				<label>평점</label>
				<div id="eva-slider-range"></div>
				<p id="eva-range"><p>
				<input name="begin-eva" type="hidden" />
				<input name="end-eva" type="hidden" />
			</div>
			
			<div>
				<label>나이</label>
				<div id="age-slider-range"></div>
				<p id="age-range"><p>
				<input name="begin-age" type="hidden" />
				<input name="end-age" type="hidden" />
			</div>
			
			<div>
				<label>Category</label>
				<input name="category" type="text"/>
			</div>
			
			<div>
				<label>Sex</label>
				<label>남</label>
				<input name="sex" type="radio" value="0" />
				<label>여</label>
				<input name="sex" type="radio" value="1" />
			</div>

			<button type="submit">적용</button>
		</form>
	</div>
</div>