<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="register" /></title>
<link href="resources/css/register.css" rel="stylesheet">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row  pad-top">

			<div
				class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Register Yourself </strong>
					</div>
					<div class="panel-body">
						<c:if test="${not empty error}">
							<div>${error}</div>
						</c:if>
						<form name='register' action="<c:url value='/register' />"
							method='POST' enctype="multipart/form-data">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" /> <br />
							<div class="form-group input-group">
								<span class="input-group-addon"><i
									class="fa fa-circle-o-notch"></i></span> <input type="text"
									name="firstName" class="form-control"
									placeholder="<fmt:message key="first.name" />" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i
									class="fa fa-circle-o-notch"></i></span> <input type="text"
									name="lastName" class="form-control"
									placeholder="<fmt:message key="last.name" />" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-tag"></i></span>
								<input type="text" class="form-control" name="userName"
									placeholder="<fmt:message key="user.name" />" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"></span> <input type="text"
									name="email" class="form-control"
									placeholder="<fmt:message key="email" />" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input type="password" class="form-control" name="password"
									placeholder="<fmt:message key="enter.password" />" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-btn"> <span
									class=" btn btn-default btn-file">Photo<input
										type="file" name=photo id="data">
								</span>
								</span> <input type="text" id="valdfil" class="form-control" readonly />
							</div>
							<div class="form-group input-group">
								<label class="radio-inline"><input type="radio"
									name="role" value="ROLE_ADMIN"> <fmt:message
										key="teacher" /></label> <label class="radio-inline"><input
									type="radio" name="role" value="ROLE_USER"> <fmt:message
										key="student" /></label>
							</div>


							<button class="btn btn-primary" type="submit">
								<fmt:message key="register.me" />
							</button>
							<hr />
							<fmt:message key="already.registered" />
							<a href="${pageContext.request.contextPath}/login"><fmt:message
									key="login.here" /></a>
						</form>
					</div>

				</div>
			</div>


		</div>
	</div>

	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
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