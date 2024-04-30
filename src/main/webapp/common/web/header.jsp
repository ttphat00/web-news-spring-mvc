<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.example.util.SecurityUtils" %>
<c:url var="categoryAPI" value="/api/category"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="<c:url value="/trang-chu?page=1&limit=3"/>">News</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul id="categories" class="navbar-nav ml-auto"></ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="<c:url value='/trang-chu?page=1&limit=3'/>">Trang chủ
						<span class="sr-only">(current)</span>
				</a></li>
				<security:authorize access = "isAnonymous()">
					<li class="nav-item"><a class="nav-link" href="<c:url value='/dang-nhap'/>">Đăng nhập</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Đăng ký</a></li>
				</security:authorize>
				<security:authorize access = "isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" href="#">
							Xin chào, ${SecurityUtils.getPrincipal().getFullName()}
									  <%-- <%=SecurityUtils.getPrincipal().getFullName()%> --%>
						</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="<c:url value='/thoat'/>">Thoát</a></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>
<script>
	$(document).ready(function(){
		$.ajax({
			url: '${categoryAPI}',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
            	var htmlElements = result.map(function(item){
            		return '<li class="nav-item"><a class="nav-link" href="#">'+item.name+'</a></li>';
            	}).join("");
            	
            	$("#categories")[0].innerHTML = htmlElements;
            },
            error: function (error) {
            	console.log(error);
            }
		});
	});
</script>