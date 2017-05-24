<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>



 <nav id="top-nav" class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
<!--                 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> -->
<!--                     <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i> -->
<!--                 </button> -->
				<button aria-controls="bs-navbar" aria-expanded="false" class="navbar-toggle collapsed" data-target="#bs-navbar" data-toggle="collapse" type="button">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>	
                <a class="navbar-brand" href="<c:url value="/"/>">어디가YOU</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-navbar">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li class="page-scroll">
                        <a href="#portfolio">Portfolio</a>
                    </li>
                    <li class="page-scroll">
                        <a href="#about">About</a>
                    </li>
                    <li class="page-scroll">
                        <a href="#contact">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>


<nav id="footer-menu" class="navbar navbar-default navbar-fixed-bottom">
	<div class="container">
		<div id="navbar" class="navbar-collapse">
			<ul class="nav text-center col-xs-12">

				<li class="dropup col-xs-3">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<div class="menu-icon">

							<img class="img-responsive center-block" src="<c:url value = "/resources/img/flaticon/like.png"/>" alt="recommend">
						</div>

					</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/recommend/"/>">주변장소 추천</a></li>
						<li><a href="<c:url value="/location/"/>">장소 검색</a></li>
						<li><a href="<c:url value="/stats/heatmap"/>">열지도</a></li>
					</ul>
				</li>
				
				<li class="dropup col-xs-3">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<div class="menu-icon">
						<img class="img-responsive center-block"
							src="<c:url value = "/resources/img/flaticon/loupe.png"/>" alt="">
						</div>

					</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/evaluation/miss-evaluations"/>">미평가 장소 평가</a></li>
						<li><a href="<c:url value="/evaluation/historys"/>">기존 평가 조회</a></li>
					</ul>
				</li>
				<li class="dropup col-xs-3">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<div class="menu-icon">
							<img class="img-responsive center-block"
							src="<c:url value = "/resources/img/flaticon/cash.png"/>" alt="">
						</div>

					</a>
					<ul class="dropdown-menu">						
						<li><a href="<c:url value="/point/pointShop"/>">포인트 사용</a></li>
					</ul>
				</li>
				
				<li class="dropup col-xs-3">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<div class="menu-icon">

							<img class="img-responsive center-block"
							src="<c:url value = "/resources/img/flaticon/wrench.png"/>" alt="">
						</div>

					</a>
					<ul class="dropdown-menu">
						<li>
							<sec:authorize access="isAuthenticated()">
								<a href="#" onclick="logout()">로그아웃</a>
								<form:form action="${pageContext.request.contextPath}/user/logout" id="logout-form" method="POST">
								</form:form>
							</sec:authorize>
							<sec:authorize access="isAnonymous()">
								<a href="<c:url value="/user/login" />">로그인</a>
							</sec:authorize>
						</li>
						<li role="separator" class="divider"></li>
						<li><a href="#" onclick="callSettingActivity()">앱 설정</a></li>
						<li><a href="<c:url value="/user/change-account" />">계정 설정</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>

<script>
	function logout(){
		$('#logout-form').submit();
	}
</script>