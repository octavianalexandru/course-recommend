<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="statistics" /></title>
<link href="resources/css/statistics.css" rel="stylesheet">

</head>
<body>
	<div id="wrapper">
		<jsp:include page="template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-folder-open fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">${numberOfCourses }</div>
										<div>All Courses!</div>
									</div>
								</div>
							</div>
							<a href="${pageContext.request.contextPath}/courses">
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-briefcase fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">${myCourses }</div>
										<div>Your Courses!</div>
									</div>
								</div>
							</div>
							<a href="${pageContext.request.contextPath}/mycourses">
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-user fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">${numberOfStudents }</div>
										<div>Students!</div>
									</div>
								</div>
							</div>
							<a href="${pageContext.request.contextPath}/sendemail">
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-plus-circle fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">${myStudents }</div>
										<div>Your Students!</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6 col-md-6">
						<div id="bar_of_pies" data-sort="true" data-width="700"
							class="jChart chart-sm" name="Your Courses per Type">
							<c:forEach items="${courseStatistics}" var="entry"
								varStatus="loop">
								<div class="define-chart-row" data-color="${colors.get(loop.index) }"
									title="${entry.key }">${entry.value}</div>
							</c:forEach>
							<div class="define-chart-footer">10</div>
							<div class="define-chart-footer">20</div>
							<div class="define-chart-footer">30</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#bar_of_pies").jChart();
		});
	</script>
	<script src="resources/js/jchart.js"></script>
</body>
</html>