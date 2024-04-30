<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page import="com.example.util.StringUtil" %>
<c:url var="webHomeURL" value="/trang-chu" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Trang chủ</title>
</head>
<body>
	<div class="container">
		<form action="<c:url value='/trang-chu'/>" id="formSubmit" method="get">
			<!-- Heading Row -->
			<div class="row align-items-center my-5" style="height: 430px">
				<div class="col-lg-7">
					<img class="img-fluid rounded mb-4 mb-lg-0" src="${model.listResult[0].thumbnail}" alt=""
						style="width: 100%; height: 100%">
				</div>
				<!-- /.col-lg-8 -->
				<div class="col-lg-5">
					<h1 class="font-weight-light">${model.listResult[0].title}</h1>
					<p>${model.listResult[0].shortDescription}</p>
				</div>
				<!-- /.col-md-4 -->
			</div>
			<!-- /.row -->
	
			<!-- Call to Action Well -->
			<div class="card text-white bg-secondary my-5 py-4 text-center">
				<div class="card-body">
					<p class="text-white m-0"></p>
				</div>
			</div>
	
			<!-- Content Row -->
			<div class="row">
				<c:forEach var="item" items="${model.listResult}">
					<div class="col-md-4 mb-5" style="height: 450px">
						<div class="card h-100">
							<div class="card-body">
								<a href="/${item.categoryCode}/${StringUtil.convertToSlug(item.title)}-${item.id}">
									<img alt="" src="${item.thumbnail}" style="width: 100%; height: 45%; margin-bottom: 10px">
								</a>
								<h4 class="card-title"><a href="/${item.categoryCode}/${StringUtil.convertToSlug(item.title)}-${item.id}" class="text-decoration-none text-dark">${item.title}</a></h4>
								<p class="card-text"><a href="/${item.categoryCode}/${StringUtil.convertToSlug(item.title)}-${item.id}" class="text-decoration-none text-dark">${item.shortDescription}</a></p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- /.row -->
			<ul class="pagination" id="pagination"></ul>
			<input type="hidden" value="" id="page" name="page"/>
			<input type="hidden" value="" id="limit" name="limit"/>
		</form>
	</div>
	<script>
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = ${model.limit};
		$(function () {
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: totalPages,
	            visiblePages: 10,
	            startPage: currentPage,
	            onPageClick: function (event, page) {
	                if(currentPage != page){
	                	$('#limit').val(limit);
						$('#page').val(page);
	                	$('#formSubmit').submit();
	                	//window.location.href = "${webHomeURL}?page="+page+"&limit="+limit;
	                }
	            }
	        });
	    });
	</script>
</body>
</html>