<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title><dec:title default="Trang chủ" /></title>

<!-- Bootstrap core CSS -->
<link href="<c:url value='/template/web/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value='/template/web/css/small-business.css'/>" rel="stylesheet">

<script src="<c:url value='/template/web/vendor/jquery/jquery.min.js'/>"></script>
<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script src="<c:url value='/template/web/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/template/web/paging/jquery.twbsPagination.js' />"></script>

</head>
<body>
	<!-- Navigation -->
	<%@ include file="/common/web/header.jsp" %>

	<!-- Page Content -->
	<dec:body />
	<!-- /.container -->

	<!-- Footer -->
	<%@ include file="/common/web/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/template/web/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
</body>
</html>