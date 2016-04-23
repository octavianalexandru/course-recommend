<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/sb-admin.css" rel="stylesheet">
<link href="resources/css/header.css" rel="stylesheet">
<link href="resources/css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">

		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/dispatch"><fmt:message
				key="course.recommend" /></a>

	</div>
	<!-- Top Menu Items -->
	<ul class="nav navbar-right top-nav">
		<sec:authorize access="hasRole('ROLE_USER')">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
					class="caret"></b></a>
				<ul class="dropdown-menu message-dropdown">
					<c:forEach items="${emails}" var="email">
						<li class="message-preview"><a href="#">
								<div class="media">
									<span class="pull-left"> <img
										class="media-object img-responsive" style="width: 50px;"
										src="${pageContext.request.contextPath}/photo/${email.teacher}"
										onerror="this.src = 'http://placehold.it/50x50'">
									</span>
									<div class="media-body">
										<h5 class="media-heading">
											<strong>${email.firstName } ${email.lastName }</strong>
										</h5>
										<p class="small text-muted">
											<i class="fa fa-clock-o"></i>
											<fmt:formatDate pattern="MM/dd/yyyy"
												value="${email.datesent}" />
										</p>
										<p>${email.message }</p>
									</div>
								</div>
						</a></li>
					</c:forEach>
					<li class="message-footer"><a
						href="${pageContext.request.contextPath}/getemails">Read All
							Messages</a></li>
				</ul></li>
		</sec:authorize>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"><i class="fa fa-user"></i> <c:out
					value='${user.firstname} ${user.lastname}' /><b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li>
					<div class="navbar-content">
						<div class="row">
							<div class="col-md-5">
								<img
									src="${pageContext.request.contextPath}/photo/${user.username}"
									onerror="this.src = 'http://placehold.it/120x120'"
									class="img-responsive" />

							</div>
							<div class="col-md-7">
								<span><c:out value='${user.firstname} ${user.lastname}' /></span>
								<p class="text-muted small">${user.email}</p>
								<div class="divider"></div>
								<a href="${pageContext.request.contextPath}/viewprofile"
									class="btn btn-primary btn-sm active"><fmt:message
										key="view.profile" /></a>
							</div>
						</div>
					</div>
					<div class="navbar-footer">
						<div class="navbar-footer-content">
							<div class="row">
								<div class="col-md-6">
									<a href="${pageContext.request.contextPath}/changepassword"
										class="btn btn-default btn-sm"><fmt:message
											key="change.password" /></a>
								</div>
								<div class="col-md-6">
									<a href="javascript:document.getElementById('logout').submit()"
										class="btn btn-default btn-sm pull-right"><fmt:message
											key="sign.out" /></a>
								</div>
							</div>
						</div>
					</div>
				</li>
			</ul></li>
	</ul>
	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav side-nav">
			<sec:authorize access="hasRole('ROLE_USER')">
				<form id="search" action="<c:url value='/search' />" method="POST">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<li><input name="searchText" class="search-box" id="searchText"><i
						class="fa fa-fw fa-search" onclick="javascript:document.getElementById('search').submit()"></i></li>
				</form>

			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="${pageContext.request.contextPath}/statistics"><i
						class="fa fa-fw fa-dashboard"></i></i> <fmt:message key="statistics" /></a></li>
			</sec:authorize>
			<li><a href="${pageContext.request.contextPath}/courses"><i
					class="fa fa-fw fa-table"></i> <fmt:message key="all.courses" /></a></li>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="${pageContext.request.contextPath}/mycourses"><i
						class="fa fa-fw fa-table"></i> <fmt:message key="my.courses" /></a></li>
				<li><a href="${pageContext.request.contextPath}/addcourse"><i
						class="fa fa-fw fa-edit"></i> <fmt:message key="add.course" /></a></li>
				<li><a href="${pageContext.request.contextPath}/sendemail"><i
						class="fa fa-envelope"></i> <fmt:message key="send.email" /></a></li>
			</sec:authorize>
		</ul>
	</div>
	<!-- /.navbar-collapse --> </nav>

	<c:url value="/logout" var="logoutUrl" />
	<form id="logout" action="${logoutUrl}" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>