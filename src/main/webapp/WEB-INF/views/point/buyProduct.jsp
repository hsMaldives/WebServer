<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<form:form commandName="delivery" method="POST">


		<div class="col-md-6 col-xs-12 buyProduct">
			<h2>배송지</h2>
			<br />

			<div class="row">
				<div id="form-group-country" class="form-group col-lg-4">
					<label class="control-label" for="country"> 배송국가 :</label>
					<form:input id="country" path="country" cssClass="form-control" />
					<form:errors id="country" path="country" cssClass="help-block" />
				</div>
			</div>
			<div class="row">
				<div id="form-group-username" class="form-group col-lg-4">
					<label class="control-label" for="username"> 받는 분 :</label>
					<form:input id="username" path="username" cssClass="form-control" />
					<form:errors id="username" path="username" cssClass="help-block" />
				</div>
			</div>
			<div class="row">
				<div id="form-group-phone" class="form-group col-lg-4">
					<label class="control-label" for="phone"> 휴대전화 :</label>
					<form:input id="phone" path="phone" cssClass="form-control" />
					<form:errors id="phone" path="phone" cssClass="help-block" />
				</div>
			</div>
			<div class="row">
				<div id="form-group-address" class="form-group col-lg-4">
					<label class="control-label" for="address"> 주소 :</label>
					<form:input id="address" path="address" cssClass="form-control" />
					<form:errors id="address" path="address" cssClass="help-block" />
				</div>
			</div>
			<div class="row">
				<div id="form-group-zipcode" class="form-group col-lg-4">
					<label class="control-label" for="zipcode"> 우편번호 :</label>
					<form:input id="zipcode" path="zipcode" cssClass="form-control" />
					<form:errors id="zipcode" path="zipcode" cssClass="help-block" />
				</div>
			</div>



		</div>

		<div class="col-md-6 col-xs-12 buyProduct">
			<h2>주문상품정보</h2>

			<br />
			<div class="col-md-3">
				<img src="<c:url value="/resources/img/${product.imageFileName }"/>"
					alt="image" style="width: 100%; height: 100%;" />
			</div>
			<div class="col-md-9">
				<p>가격 : ${product.price } point</p>
				<p>갯수 : 1</p>
				<p>이름 : ${product.name }</p>
				<p>설명 : ${product.description }</p>
				<p></p>
			</div>
		</div>



		<div class="col-md-6 col-xs-12 buyProduct">


			<h2>최종결제정보</h2>
			<br />
			<p>상품가격 : ${product.price } point</p>
			<p>할인가격 : 0</p>
			<p>배송비 : 0</p>
			<p>현재보유포인트 : ${user.point} point</p>
			<p>결제예정액 : ${product.price } point</p>			
			<p>남는 포인트 : ${user.point - product.price } point</p>
			<button type="submit" class="btn btn-default">결제하기</button>
		</div>


	</form:form>

</div>



