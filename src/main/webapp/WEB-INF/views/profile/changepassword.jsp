<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="change.password" /></title>
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
					<form id="changepassword" name='changepassword'
						action="<c:url value='/changepassword' />" method='POST'
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
									<c:if test="${not empty error}">
										<div>${error}</div>
									</c:if>
									<c:if test="${not empty message}">
										<div>${message}</div>
									</c:if>
								</div>
								<div class=" col-md-9 col-lg-9 ">

									<table class="table table-user-information">
										<tbody>
											<tr>
												<td><fmt:message key="current.password" />:</td>
												<td><input type="text" name="currentPassword"
													class="form-control"
													placeholder="<fmt:message key="current.password" />" /></td>
											</tr>
											<tr>
												<td><fmt:message key="new.password" />:</td>
												<td><input type="text" name="newPassword"
													class="form-control"
													placeholder="<fmt:message key="new.password" />" /></td>
											</tr>
											<tr>
												<td><fmt:message key="retype.new.password" />:</td>
												<td><input type="text" name="retypeNewPassword"
													class="form-control"
													placeholder="<fmt:message key="retype.new.password" />" /></td>
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
							href="javascript:document.getElementById('changepassword').submit()"
							class="btn btn-sm btn-primary"><i class="fa fa-floppy-o"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>