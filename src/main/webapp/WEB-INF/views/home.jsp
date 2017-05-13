<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Theme CSS -->
<link href="<c:url value = "/resources/css/freelancer.min.css"/>" rel="stylesheet">

<!-- Header -->
<header>
    <div class="container" id="maincontent" tabindex="-1">
        <div class="row">
            <div class="col-lg-12">
                <img class="img-responsive" src="<c:url value = "/resources/img/profile.png"/>" alt="">
                <div class="intro-text">
                    <h1 class="name">어디가YOU</h1>
                    <hr class="star-light">
                    <span class="skills">Team Maldives - Hansung CSE</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Portfolio Grid Section -->
<section id="portfolio">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>Recommend</h2>
                <hr class="star-primary">
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4 portfolio-item">
                <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                    <div class="caption">
                        <div class="caption-content">
                            <i class="fa fa-search-plus fa-3x"></i>
                        </div>
                    </div>
                    <img src="<c:url value = "/resources/img/portfolio/cabin.png"/>" class="img-responsive" alt="Cabin">
                </a>
            </div>
            <div class="col-sm-4 portfolio-item">
                <a href="#portfolioModal2" class="portfolio-link" data-toggle="modal">
                    <div class="caption">
                        <div class="caption-content">
                            <i class="fa fa-search-plus fa-3x"></i>
                        </div>
                    </div>
                    <img src="<c:url value = "/resources/img/portfolio/cake.png"/>" class="img-responsive" alt="Slice of cake">
                </a>
            </div>
            <div class="col-sm-4 portfolio-item">
                <a href="#portfolioModal3" class="portfolio-link" data-toggle="modal">
                    <div class="caption">
                        <div class="caption-content">
                            <i class="fa fa-search-plus fa-3x"></i>
                        </div>
                    </div>
                    <img src="<c:url value = "/resources/img/portfolio/circus.png"/>" class="img-responsive" alt="Circus tent">
                </a>
            </div>
            <div class="col-sm-4 portfolio-item">
                <a href="#portfolioModal4" class="portfolio-link" data-toggle="modal">
                    <div class="caption">
                        <div class="caption-content">
                            <i class="fa fa-search-plus fa-3x"></i>
                        </div>
                    </div>
                    <img src="<c:url value = "/resources/img/portfolio/game.png"/>" class="img-responsive" alt="Game controller">
                </a>
            </div>
            <div class="col-sm-4 portfolio-item">
                <a href="#portfolioModal5" class="portfolio-link" data-toggle="modal">
                    <div class="caption">
                        <div class="caption-content">
                            <i class="fa fa-search-plus fa-3x"></i>
                        </div>
                    </div>
                    <img src="<c:url value = "/resources/img/portfolio/safe.png"/>" class="img-responsive" alt="Safe">
                </a>
            </div>
            <div class="col-sm-4 portfolio-item">
                <a href="#portfolioModal6" class="portfolio-link" data-toggle="modal">
                    <div class="caption">
                        <div class="caption-content">
                            <i class="fa fa-search-plus fa-3x"></i>
                        </div>
                    </div>
                    <img src="<c:url value = "/resources/img/portfolio/submarine.png"/>" class="img-responsive" alt="Submarine">
                </a>
            </div>
        </div>
    </div>
</section>

<%-- <div class="container">
	
	<div class ="text-center">
	<h2>MALDIVES</h2>
	<p></p>
		<img src="<c:url value = "/resources/img/starwars.jpg"/>"
			class="img-circle" alt="Cinque Terre" style="width: 80%">
	</div>
</div> --%>
