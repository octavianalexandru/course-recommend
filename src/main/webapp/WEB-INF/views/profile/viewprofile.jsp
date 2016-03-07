<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<html>
<head>
<title><fmt:message key="view.profile" /></title>
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
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<img
									src="${pageContext.request.contextPath}/photo/${user.username}"
									onerror="this.src = 'http://placehold.it/300x300'"
									class="img-circle img-responsive">
							</div>
							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td><fmt:message key="user.name" />:</td>
											<td><c:out value='${user.username}' /></td>
										</tr>
										<tr>
											<td><fmt:message key="first.name" />:</td>
											<td><c:out value='${user.firstname}' /></td>
										</tr>
										<tr>
											<td><fmt:message key="last.name" />:</td>
											<td><c:out value='${user.lastname}' /></td>
										</tr>
										<tr>
											<td><fmt:message key="email" />:</td>
											<td><c:out value='${user.email}' /></td>
										</tr>
									</tbody>
								</table>


							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a data-original-title="Broadcast Message" data-toggle="tooltip"
							type="button"
							href="${pageContext.request.contextPath}/editprofile"
							class="btn btn-sm btn-primary"><i class="fa fa-fw fa-edit"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
