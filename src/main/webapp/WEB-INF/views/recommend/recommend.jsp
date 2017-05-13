<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<nav class="navbar navbar-inverse" data-spy="affix"
		data-offset-top="197">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/"/>">어디가YOU</a>
			</div>
			<div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li><a href="#section1">Section 1</a></li>
						<li><a href="#section2">Section 2</a></li>
						<li><a href="#section3">Section 3</a></li>
						<li><a href="#section4">Section 4</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>

	<!-- 	<div class="dropdown"> -->
	<!-- 		<button class="btn btn-default dropdown-toggle " type="button" -->
	<!-- 			id="menu1" data-toggle="dropdown"> -->
	<!-- 			원하시는 추천 서비스 <span class="caret"></span> -->
	<!-- 		</button> -->
	<!-- 		<ul class="dropdown-menu " role="menu" aria-labelledby="menu1"> -->
	<!-- 			<li role="presentation"><a role="menuitem" tabindex="-1" -->
	<!-- 				href="#section1">Item-based</a></li> -->
	<!-- 			<li role="presentation"><a role="menuitem" tabindex="-1" -->
	<!-- 				href="#section2">User-based</a></li> -->
	<!-- 			<li role="presentation"><a role="menuitem" tabindex="-1" -->
	<!-- 				href="#section3">#</a></li> -->
	<!-- 			<li role="presentation" class="divider"></li> -->
	<!-- 			<li role="presentation"><a role="menuitem" tabindex="-1" -->
	<!-- 				href="#section4">About Us</a></li> -->
	<!-- 		</ul> -->
	<!-- 	</div> -->




	<div id="section1" class="container-fluid">
		<h1>Section 1</h1>
		<p>
			<c:forEach var="store" items="${stores }">
				<p>name : ${store.name }</p>
				<p>code : ${store.code }</p>
				<p>address : ${store.address }</p>
				<p>latitude : ${store.latitude }</p>
				<p>longitude : ${store.longitude }</p>
				<br />
				<hr />
			</c:forEach>
		</p>
	</div>
	<div id="section2" class="container-fluid">
		<h1>Section 2</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>
	<div id="section3" class="container-fluid">
		<h1>Section 3</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>
	<div id="section4" class="container-fluid">
		<h1>Section 4</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>









</div>