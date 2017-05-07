<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="page-header">
	<h1>계정설정</h1>
</div>
<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="${pageContext.request.contextPath }/user/change-account" commandName="changeAccount" method="POST">
			<div class="row">
				<c:if test="${user.signInProvider != null}">
					<div class="form-group col-lg-4">
						<label class="control-label" >SNS:</label>
						<p>${user.signInProvider}</p>
					</div>
				</c:if>
				<div id="form-group-email" class="form-group col-lg-4">
					<label class="control-label">Email:</label>
					<p>${user.email }</p>
				</div>
				<div class="form-group col-lg-4">
					<label class="control-label" for="user-name">이름:</label>
					<p>${user.name }</p>
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
						<option value="">직업을 선택해 주세요</option>
						<form:options items	="${jobList }" itemValue="job_idx" itemLabel="name"/>
					</form:select>
					
					<form:errors id="error-job_idx" path="job_idx" cssClass="help-block" />
				</div>
			</div>
			
			
			<button type="submit" class="btn btn-default">
				수정
			</button>
		</form:form>
	</div>
</div>