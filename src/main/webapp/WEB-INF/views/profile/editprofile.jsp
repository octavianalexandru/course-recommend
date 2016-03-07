<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<html>
<head>
<title><fmt:message key="edit.profile" /></title>
<link href="resources/css/editprofile.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<c:out value='${user.firstname} ${user.lastname}' />
						</h3>
					</div>
					<form id="saveprofile" name='saveprofile'
						action="<c:url value='/editprofile' />" method='POST'
						enctype="multipart/form-data">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3 col-lg-3 " align="center">
									<img
										src="${pageContext.request.contextPath}/photo/${user.username}"
										onerror="this.src = 'http://placehold.it/300x300'"
										class="img-circle img-responsive">
									<div class="form-group input-group">
										<span class="input-group-btn"> <span
											class=" btn btn-default btn-file">Photo<input
												type="file" name=photo id="data">
										</span>
										</span> <input type="text" id="valdfil" class="form-control" readonly />
									</div>
								</div>
								<div class=" col-md-9 col-lg-9 ">

									<table class="table table-user-information">
										<tbody>
											<tr>
												<td><fmt:message key="first.name" />:</td>
												<td><input type="text" name="firstName"
													class="form-control"
													placeholder="<fmt:message key="first.name" />" /></td>
											</tr>
											<tr>
												<td><fmt:message key="last.name" />:</td>
												<td><input type="text" name="lastName"
													class="form-control"
													placeholder="<fmt:message key="last.name" />" /></td>
											</tr>
											<tr>
												<td><fmt:message key="email" />:</td>
												<td><input type="text" name="email"
													class="form-control"
													placeholder="<fmt:message key="email" />" /></td>
											</tr>
										</tbody>
									</table>


								</div>
							</div>
						</div>
					</form>
					<div class="panel-footer">
						<a data-original-title="Broadcast Message" data-toggle="tooltip"
							type="button"
							href="javascript:document.getElementById('saveprofile').submit()"
							class="btn btn-sm btn-primary"><i class="fa fa-floppy-o"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$(document)
									.on(
											'change',
											'.btn-file :file',
											function() {
												var input = $(this), numFiles = input
														.get(0).files ? input
														.get(0).files.length
														: 1, label = input
														.val().replace(/\\/g,
																'/').replace(
																/.*\//, '');
												input.trigger('fileselect', [
														numFiles, label ]);
											});

							$('.btn-file :file').on('fileselect',
									function(event, numFiles, label) {
										console.log(numFiles);
										console.log(label);
										$("#valdfil").val(label);
									});
						});
	</script>
</body>
</html>
