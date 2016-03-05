<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<html>
<head>
<title><fmt:message key="home" /></title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/sb-admin.css" rel="stylesheet">
<link href="resources/css/editprofile.css" rel="stylesheet">
<link href="resources/css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/dispatch"><fmt:message
						key="course.recommend" /></a>
			</div>
			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
						class="caret"></b></a>
					<ul class="dropdown-menu message-dropdown">
						<li class="message-preview"><a href="#">
								<div class="media">
									<span class="pull-left"> <img class="media-object"
										src="http://placehold.it/50x50" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading">
											<strong>John Smith</strong>
										</h5>
										<p class="small text-muted">
											<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
										</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
						</a></li>
						<li class="message-preview"><a href="#">
								<div class="media">
									<span class="pull-left"> <img class="media-object"
										src="http://placehold.it/50x50" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading">
											<strong>John Smith</strong>
										</h5>
										<p class="small text-muted">
											<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
										</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
						</a></li>
						<li class="message-preview"><a href="#">
								<div class="media">
									<span class="pull-left"> <img class="media-object"
										src="http://placehold.it/50x50" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading">
											<strong>John Smith</strong>
										</h5>
										<p class="small text-muted">
											<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
										</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
						</a></li>
						<li class="message-footer"><a href="#">Read All New
								Messages</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-bell"></i> <b
						class="caret"></b></a>
					<ul class="dropdown-menu alert-dropdown">
						<li><a href="#">Alert Name <span
								class="label label-default">Alert Badge</span></a></li>
						<li><a href="#">Alert Name <span
								class="label label-primary">Alert Badge</span></a></li>
						<li><a href="#">Alert Name <span
								class="label label-success">Alert Badge</span></a></li>
						<li><a href="#">Alert Name <span class="label label-info">Alert
									Badge</span></a></li>
						<li><a href="#">Alert Name <span
								class="label label-warning">Alert Badge</span></a></li>
						<li><a href="#">Alert Name <span
								class="label label-danger">Alert Badge</span></a></li>
						<li class="divider"></li>
						<li><a href="#">View All</a></li>
					</ul></li>
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
											class="btn btn-primary btn-sm active">View Profile</a>
									</div>
								</div>
							</div>
							<div class="navbar-footer">
								<div class="navbar-footer-content">
									<div class="row">
										<div class="col-md-6">
											<a href="#" class="btn btn-default btn-sm">Change
												Passowrd</a>
										</div>
										<div class="col-md-6">
											<a
												href="javascript:document.getElementById('logout').submit()"
												class="btn btn-default btn-sm pull-right">Sign Out</a>
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
					<li><a href="index.html"><i class="fa fa-fw fa-dashboard"></i></i>
							<fmt:message key="statistics" /></a></li>
					<li><a href="tables.html"><i class="fa fa-fw fa-table"></i>
							<fmt:message key="courses" /></a></li>
					<li><a href="forms.html"><i class="fa fa-fw fa-edit"></i>
							<fmt:message key="add.course" /></a></li>
					<li><a href="bootstrap-elements.html"><i
							class="fa fa-envelope"></i> <fmt:message key="send.email" /></a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<c:out value='${user.firstname} ${user.lastname}' />
						</h3>
					</div>
					<form name='saveprofile' action="<c:url value='/editprofile' />"
							method='POST' enctype="multipart/form-data">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<img
									src="${pageContext.request.contextPath}/photo/${user.username}"
									onerror="this.src = 'http://placehold.it/300x300'"
									class="img-circle img-responsive">
								<div class="form-group input-group">
								<span
									class="input-group-btn"> <span
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
											<td>User Name:</td>
											<td><input type="text" class="form-control"
												name="userName"
												placeholder="<fmt:message key="user.name" />" /></td>
										</tr>
										<tr>
											<td>First Name:</td>
											<td><input type="text" name="firstName"
												class="form-control"
												placeholder="<fmt:message key="first.name" />" /></td>
										</tr>
										<tr>
											<td>Last Name:</td>
											<td><input type="text" name="lastName"
												class="form-control"
												placeholder="<fmt:message key="last.name" />" /></td>
										</tr>
										<tr>
											<td>Email:</td>
											<td><input type="text" name="email" class="form-control"
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
							type="button" href="javascript:document.getElementById('saveprofile').submit()" class="btn btn-sm btn-primary"><i
							class="fa fa-floppy-o"></i></a>

					</div>

				</div>
			</div>
		</div>
	</div>
	<c:url value="/logout" var="logoutUrl" />
	<form id="logout" action="${logoutUrl}" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
