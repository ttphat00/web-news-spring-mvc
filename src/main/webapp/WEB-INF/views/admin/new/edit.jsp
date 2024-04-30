<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="newURL" value="/quan-tri/bai-viet/danh-sach"/>
<c:url var="editNewURL" value="/quan-tri/bai-viet/chinh-sua"/>
<c:url var="newAPI" value="/api/new"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Trang biên tập</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang chủ</a>
					</li>

					<li><a href="#">Forms</a></li>
					<li class="active">Thêm bài viết</li>
				</ul>
				<!-- /.breadcrumb -->

				<!-- <div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon"> <input type="text"
							placeholder="Search ..." class="nav-search-input"
							id="nav-search-input" autocomplete="off" /> <i
							class="ace-icon fa fa-search nav-search-icon"></i>
						</span>
					</form>
				</div> -->
				<!-- /.nav-search -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<c:if test="${not empty param.message}">
							<div class="alert alert-${alert}">
	  							${message}
							</div>
						</c:if>
						<!-- PAGE CONTENT BEGINS -->
						<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="categoryCode">Thể loại</label>
								<div class="col-sm-9">
									<form:select class="col-xs-10 col-sm-5" id="categoryCode" path="categoryCode">
										<form:option value="" label="--Chọn thể loại--"/>
										<form:options items="${categories}"/>
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="title">Tiêu đề</label>
								<div class="col-sm-9">
									<form:input id="title" path="title" type="text" placeholder="title" cssClass="col-xs-10 col-sm-5"/>
									<!-- Default type of form:input is TEXT -->
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="file">Hình ảnh</label>
								<div class="col-sm-9">
									<input type="file" id="file" name="file" class="col-xs-10 col-sm-5" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="shortDescription">Mô tả</label>
								<div class="col-sm-9">
									<form:textarea id="shortDescription" path="shortDescription" class="col-xs-10 col-sm-5" rows="5" cols=""/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="content">Nội dung</label>
								<div class="col-sm-9">
									<form:textarea id="content" path="content" cssClass="col-xs-10 col-sm-5" rows="5" cols=""/>
								</div>
							</div>
							<form:hidden path="id" id="newId"/>
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:if test="${not empty model.id}">
										<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
											<i class="ace-icon fa fa-check bigger-110"></i> Cập nhật
										</button>
									</c:if>
									<c:if test="${empty model.id}">
										<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
											<i class="ace-icon fa fa-check bigger-110"></i> Thêm
										</button>
									</c:if>

									&nbsp; &nbsp; &nbsp;
									<button class="btn" type="reset">
										<i class="ace-icon fa fa-undo bigger-110"></i> Hủy
									</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#btnAddOrUpdateNew').click(function (e) {
			e.preventDefault();
			
			var formData = $('#formSubmit').serializeArray();
			//console.log(formData);
			var data = {};
			$.each(formData, function(index, value){
				data[value.name] = value.value;
			});
			//console.log(data);
			
			var formDataForAdding = new FormData();
			$.each(formData, function(index, value){
				formDataForAdding.append(value.name, value.value);
			});
			formDataForAdding.append('file', $('input[type=file]')[0].files[0]);
			
			var id = $('#newId').val();
			if(id == ""){
				addNew(formDataForAdding);
			}else{
				updateNew(data);
			}
		});
		
		function addNew(data) {
			$.ajax({
	            url: '${newAPI}',
	            type: 'POST',
	            processData: false,
	            contentType: false,
	            data: data,
	            dataType: 'json',
	            success: function (result) {
	            	window.location.href = "${editNewURL}?message=insert_success";
	            },
	            error: function (error) {
	            	window.location.href = "${newURL}?page=1&limit=2&message=error_system";
	            }
	        });
		}
		
		function updateNew(data) {
			$.ajax({
				url: '${newAPI}',
	            type: 'PUT',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            dataType: 'json',
	            success: function (result) {
	            	window.location.href = "${editNewURL}?id="+result.id+"&message=update_success";
	            },
	            error: function (error) {
	            	window.location.href = "${newURL}?page=1&limit=2&message=error_system";
	            }
			});
		}
	</script>
</body>
</html>