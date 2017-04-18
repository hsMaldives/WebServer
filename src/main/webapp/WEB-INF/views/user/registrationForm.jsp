<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="page-header">
	<h1>회원가입 Form</h1>
</div>
<sec:authorize access="isAnonymous()">
	<div class="panel panel-default">
		<div class="panel-body">
			<form:form action="${pageContext.request.contextPath }/user/register" commandName="user" method="POST">
				<c:if test="${user.signInProvider != null}">
					<form:hidden path="signInProvider" />
				</c:if>
				
				<div class="row">
					<div id="form-group-email" class="form-group col-lg-4">
						<label class="control-label" for="user-email">
							Email:</label>
						<form:input id="user-email" path="email" cssClass="form-control" />
						<form:errors id="error-email" path="email" cssClass="help-block" />
					</div>
				</div>
				
				<div class="row">
					<div id="form-group-firstName" class="form-group col-lg-4">
						<label class="control-label" for="user-name">이름:</label>
						<form:input id="user-name" path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="help-block" />
					</div>
				</div>
				
				<div class="row">
					<div id="form-group-lastName" class="form-group col-lg-4">
						<label class="control-label" for="user-nickname">닉네임:</label>
						<form:input id="user-nickname" path="nickname" cssClass="form-control" />
						<form:errors id="error-nickname" path="nickname" cssClass="help-block" />
					</div>
				</div>
				
				<c:if test="${user.signInProvider == null}">
					<div class="row">
						<div id="form-group-password" class="form-group col-lg-4">
							<label class="control-label" for="user-password">Password:</label>
							<form:password id="user-password" path="password" cssClass="form-control" />
							<form:errors id="error-password" path="password" cssClass="help-block" />
						</div>
					</div>
					<div class="row">
						<div id="form-group-passwordVerification"
							class="form-group col-lg-4">
							<label class="control-label" for="user-passwordVerification">PasswordVerification:</label>
							<form:password id="user-passwordVerification" path="passwordVerification" cssClass="form-control" />
							<form:errors id="error-passwordVerification" path="passwordVerification" cssClass="help-block" />
						</div>
					</div>
				</c:if>
				
				<div class="row">
					<div id="form-group-lastName" class="form-group col-lg-4">
						<label class="control-label" for="user-age">나이:</label>
						<form:input id="user-age" path="age" type="number" cssClass="form-control" />
						<form:errors id="error-age" path="age" cssClass="help-block" />
					</div>
				</div>
				
				<div class="row">
					<div id="form-group-lastName" class="form-group col-lg-4">
						<label class="control-label" for="user-sex">성별:</label>
						
						<label class="radio-inline">
							<form:radiobutton path="sex" value="men" label="남자" />
						</label>
						<label class="radio-inline">
						<form:radiobutton path="sex" value="women" label="여자" />
						</label>
					
						<form:errors id="error-sex" path="sex" cssClass="help-block" />
					</div>
				</div>
				
				<div class="row">
					<div id="form-group-lastName" class="form-group col-lg-4">
						<label class="control-label" for="user-job_idx">직업:</label>
						<form:select  path="job_idx" cssClass="form-control">
							<form:option value="#1" label="학생" selected="true"/>
							<form:option value="#2" label="회사원"/>
							<form:option value="#3" label="주부"/>
							<form:option value="#4" label="무직"/>
							<form:option value="#5" label="기타"/>
							<%--<form:options items	="${jobs }"/> --%>
						</form:select>

<%-- 						<form:input id="user-job_idx" path="job_idx" type="number" cssClass="form-control" /> --%>
<%-- 						<form:errors id="error-job_idx" path="job_idx" cssClass="help-block" /> --%>
					</div>
				</div>
				
				
				<button type="submit" class="btn btn-default">
					가입
				</button>
			</form:form>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<p>
		이미 로그인 된 사용자입니다.
	</p>
</sec:authorize>