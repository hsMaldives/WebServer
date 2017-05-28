<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container-wrapper">

	<div class="container">

		<h2>Recommend Detail Page</h2>
		<p class="lead"></p>

		<div class="col-xs-12">

			<div class="">
				<img class="center-block img-rounded " src="<c:url value="${store.imageUrl }"/>"
					alt="image" style="width: 80%" />
			</div>
			
			<br />
			
			<table class="table table-striped">
				<tbody>
					<tr>
						<th>name</th>
						<td>${store.name }</td>
					</tr>
					<tr>
						<th>category</th>
						<td>${store.category.name }</td>
					</tr>
					<tr>
						<th>phone</th>
						<td>${store.phone }</td>
					</tr>
					<tr>
						<th>address</th>
						<td>${store.address }</td>
					</tr>
					<tr>
						<th>latitude</th>
						<td>${store.latitude }</td>
					</tr>
					<tr>
						<th>longitude</th>
						<td>${store.longitude }</td>
					</tr>

					<tr>
						<th>평균평점</th>
						<td>${store.avgEvaluation}</td>
					</tr>

				</tbody>
			</table>

			## 지도를 넣어주세요 ##
			
			<ul class="list-group">
				<c:forEach var="comment" items="${store.comments }">
					<li>
						<span><c:out value="${comment.comment }" /></span>
						<span>${comment.user.nickname }</span>
						<span>${comment.timestamp }</span>
						<c:if test="${comment.user.email eq pageContext.request.userPrincipal.name }">
							<span><span class="glyphicon glyphicon-remove" aria-hidden="true" onclick="deleteComment(${comment.storeCommentIdx })"></span></span>
						</c:if>
					</li>
				</c:forEach>
			</ul>
			
			<form id="comment-form" action="<c:url value="/location/detail/${store.storeIdx}/comment/" />" method="POST">
				<input type="text" name="comment" />
				<button type="submit">댓글 달기</button>
			</form>
			
			<script>
				$(function (){
					$('#comment-form').submit(function (event){
						$.ajax({
							url: $('#comment-form').attr('action'),
							method: 'POST',
							data: $('#comment-form').serialize(),
							success: function(data){
								alert("댓글이 성공적으로 전송되었습니다.");
								location.reload()
							},
							error: function (error){
								alert("[Error] 댓글 전송중 오류가 발생하였습니다.");
							}
						});
						
						event.preventDefault();
					});
				});
				
				function deleteComment(commentIdx){
					$.ajax({
						url: '<c:url value="/location/detail/${store.storeIdx}/comment/" />' + commentIdx,
						method: 'DELETE',
						success: function(data){
							alert("댓글이 성공적으로 삭제되었습니다.");
							location.reload()
						},
						error: function (error){
							alert("[Error] 댓글 삭제중 오류가 발생하였습니다.");
						}
					});
				}
			</script>
		</div>
	</div>
</div>





