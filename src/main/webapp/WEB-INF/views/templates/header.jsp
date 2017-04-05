<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default navbar-fixed-bottom">
	<div class="container">
		<div id="navbar" class="navbar-collapse">
			<ul class="nav text-center col-xs-12">
				<li class="dropup col-xs-3">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<div class="menu-icon">
							<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
						</div>
						<div class="menu-keyword">
							<span class="menu-keyword">추천</span>
						</div>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">주변장소 추천</a></li>
						<li><a href="#">장소 검색</a></li>
					</ul>
				</li>
				
				<li class="dropup col-xs-3">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<div class="menu-icon">
							<span class="glyphicon glyphicon-check" aria-hidden="true"></span>						
						</div>
						<div class="menu-keyword">						
							평가
						</div>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">미평가 장소 평가</a></li>
						<li><a href="#">기존 평가 조회</a></li>
					</ul>
				</li>
				<li class="dropup col-xs-3">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<div class="menu-icon">
							<span class="glyphicon glyphicon-piggy-bank" aria-hidden="true"></span>
						</div>
						<div class="menu-keyword">
							<span class="menu-keyword">포인트</span>
						</div>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">포인트 조회</a></li>
						<li><a href="#">상품 구매</a></li>
					</ul>
				</li>
				
				<li class="dropup col-xs-3">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<div class="menu-icon">
							<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
						</div>
						<div class="menu-keyword">
							<span class="menu-keyword">설정</span>
						</div>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">로그아웃</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">위치 설정</a></li>
						<li><a href="#">알림 설정</a></li>
						<li><a href="#">계정 설정</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>