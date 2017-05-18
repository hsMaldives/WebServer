<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<div class="container">
	<div class="page-header">
		<h1>Login</h1>
	</div>
	<sec:authorize access="isAnonymous()">
		<div class="panel panel-default">
			<div class="panel-body">
				<h2>
					Login Form
				</h2>
				<c:if test="${param.error eq 'bad_credentials'}">
					<div class="alert alert-danger alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						로그인을 할 수 없습니다.
					</div>
				</c:if>
				<c:if test="${not empty error}">
					<div class="alert alert-danger alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						아이디 또는 비밀번호를 다시 확인하세요.
					</div>
				</c:if>
				<form action="<c:url value="/user/login"/>" method="POST" role="form">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
					<div class="row">
						<div id="form-group-email" class="form-group col-lg-4">
							<label class="control-label" for="user-email">Email:</label>
							<input id="user-email" name="username" type="text" class="form-control" />
						</div>
					</div>
	
					<div class="row">
						<div id="form-group-password" class="form-group col-lg-4">
							<label class="control-label" for="user-password">Password</label>
							<input id="user-password" name="password" type="password" class="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-4">
							<button type="submit" class="btn btn-default">
								로그인
							</button>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="form-group col-lg-4">
						<a href="<c:url value="/user/register"/>">Register</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<h2>
					Social Login
				</h2>
				<div class="row social-button-row">
					<div class="col-lg-4">
						<a href="<c:url value="/auth/facebook"/>">
							<button class="btn btn-facebook">
								<i class="fa fa-facebook" aria-hidden="true"></i> | Facebook Sign In
							</button>
						</a>
					</div>
				</div>
			</div>
		</div>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<p>
			이미 로그인이 되어있습니다.
		</p>
	</sec:authorize>
</div>