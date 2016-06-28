<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="course.details" /></title>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet">
<link href="resources/css/star-rating/star-rating.css" media="all" rel="stylesheet" type="text/css" />
<link href="resources/css/star-rating/theme-krajee-svg.css" media="all" rel="stylesheet" type="text/css" />
<link href="resources/css/details.css" rel="stylesheet">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.js"></script>
<script src="resources/js/star-rating/star-rating.js" type="text/javascript"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
		<div id="page-wrapper">
			<div class="row">
				<div class="rounded-div">
					<img
						src="${pageContext.request.contextPath}/cover/${fullCourse.id}"
						onerror="this.src = 'http://placehold.it/120x120'"
						class="img-responsive" />
					<h3>${fullCourse.title}</h3>
					<h5>Category: ${fullCourse.courseTypeDescription}</h5>
					<h5>Teacher: ${teacherName}</h5>
					<hr />
					<div class="description">${fullCourse.description}</div>
					<hr />
					<div class="pull-left">
						<p>
							Start Date:
							<fmt:formatDate pattern="dd-MMM-yyyy"
								value="${fullCourse.startDate}" />
						</p>
						<p>
							End Date:
							<fmt:formatDate pattern="dd-MMM-yyyy"
								value="${fullCourse.endDate}" />
						</p>
						<p>
							Duration:
							<c:out value="${fullCourse.numberOfWeeks}" />
							weeks
							
						</p>
					<sec:authorize access="hasRole('ROLE_USER')">
					<form id="ratingform" action="<c:url value = '/ratecourse'/>"
									method="post" class="col-sm-6">

									
									<input id="input-1" name="rating" value="${courseRating}"
										class="rating rating-loading" data-min="0" data-max="5"
										data-step="1" data-show-clear="false"
										data-show-caption="false" data-size="sm"> 
										<input
										type="hidden" name="courseId" value="${fullCourse.id}">
										 <input
										type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>
								<div class="col-sm-6"
									style="padding-top: 3%; padding-left: 22%">
									<button class="btn btn-xs btn-info" type="submit"
										form="ratingform" style="margin: auto">
										<fmt:message key="rate.it" />
									</button>
								</div>
					</div>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_USER')">
						<div class="pull-right">
							<c:choose>
								<c:when test="${register == 0 }">
									<a
										href="${pageContext.request.contextPath}/register/${fullCourse.id}">Register
										to this course !</a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/unregister/${fullCourse.id}">Unregister
										this course !</a>
								</c:otherwise>
							</c:choose>
						</div>
					</sec:authorize>
				</div>
			</div>
		</div>
	</div>
</body>
</html>