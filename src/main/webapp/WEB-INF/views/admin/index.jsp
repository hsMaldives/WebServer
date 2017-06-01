<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<h2>관리자 페이지</h2>
	
	<div class="row">
		<div class="col-md-6 admin-panel">
			<form action="<c:url value="/user/backdoor" />" method="patch">
				
				<h3>사용자 계정 접속</h3>
			
				<div class="input-group">
					<input name="userIdx" type="text" class="form-control" placeholder="사용자 IDX" />
					<span class="input-group-btn">
						<button type="submit" class="btn btn-default">전환</button>
					</span>
				</div>
			</form>
		</div>
		<div class="col-md-6 admin-panel">
			<h3>모델 생성</h3>
			
			<button type="button" class="btn btn-default" id="ibcfBtn">IBCF 모델 생성</button>
			<button type="button" class="btn btn-default" id="ubcfBtn">UBCF 모델 생성</button>
		</div>
		<div class="col-md-6 admin-panel">
			<h3>IBCF 기준 평점 수정</h3>
			<p>현재 기준 평점: <span id="now-criterion-rate">${criterionRate}</span></p>
			<form action="<c:url value="/admin/changeCriterionRate" />" id="change-criterion-rate-form" method="patch">
				<div class="input-group">
					<input name="criterionRate" type="text" class="form-control" placeholder="사용자 IDX" />
					<span class="input-group-btn">
						<button type="submit" class="btn btn-default">변경</button>
					</span>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	$(function (){
		 $('#ibcfBtn').on('click', function () {
		    var $btn = $(this).button('loading');
		    
		    $.ajax({
				url: '<c:url value="/admin/createIbcf"/>',
				success: function (data){
					if(data < 0){
						alert('IBCF 모델 생성 중 오류 발생! \n운영환경인지 확인해 주세요!');
					} else {
						alert('IBCF 모델 생성 완료 \n(경과시간: ' + data + 'ms)');
					}
					
					$btn.button('reset');
				},
				error: function (jqXHR, textStatus, errorThrown){
					alert("IBCF 모델 생성 중 오류가 발생하였습니다.");
					
					$btn.button('reset');
				}
			});
		});
		 
		$('#ubcfBtn').on('click', function () {
		    var $btn = $(this).button('loading');
		    
		    $.ajax({
				url: '<c:url value="/admin/createUbcf"/>',
				success: function (data){
					if(data < 0){
						alert('UBCF 모델 생성 중 오류 발생! \n운영환경인지 확인해 주세요!');
					} else {
						alert('UBCF 모델 생성 완료 \n(경과시간: ' + data + 'ms)');
					}
					
					$btn.button('reset');
				},
				error: function (jqXHR, textStatus, errorThrown){
					alert("UBCF 모델 생성 중 오류가 발생하였습니다.");
					
					$btn.button('reset');
				}
			});
		});
		
		$('#change-criterion-rate-form').submit(function (event){
			$.ajax({
				url: $(this).attr("action"),
				data: $(this).serialize(),
				method: $(this).attr("method"),
				success: function (data){
					alert('IBCF 기준 평점 변경이 완료되었습니다\n(변경된 값: ' + data + ')');
					$('#now-criterion-rate').text(data);
				},
				error: function (jqXHR, textStatus, errorThrown){
					alert("IBCF 기준 평점 변경 중 오류가 발생하였습니다.");
				}
			});
			
			event.preventDefault();
		});
	});
</script>