<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>



<nav id="top-nav" class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<!--                 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> -->
			<!--                     <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i> -->
			<!--                 </button> -->
			<button aria-controls="bs-navbar" aria-expanded="false"
				class="navbar-toggle collapsed" data-target="#bs-navbar"
				data-toggle="collapse" type="button">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/"/>">어디가YOU</a>
		</div>

		
	</div>
	<!-- /.container-fluid -->
</nav>