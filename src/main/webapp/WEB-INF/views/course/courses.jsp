<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><fmt:message key="courses" /></title>
<link href="resources/css/bootstrap.css" rel="stylesheet" />
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css"
	rel="stylesheet">
<link href="resources/css/star-rating/star-rating.css" media="all"
	rel="stylesheet" type="text/css" />
<link href="resources/css/star-rating/theme-krajee-svg.css" media="all"
	rel="stylesheet" type="text/css" />
<link href="resources/css/courses.css" rel="stylesheet">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.js"></script>
<script src="resources/js/star-rating/star-rating.js"
	type="text/javascript"></script>


</head>
<body>
	<div id="wrapper">
		<jsp:include page="../template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
		<div id="page-wrapper">
			<c:forEach items="${coursesByType.entrySet()}" var="entry"
				varStatus="loop">
				<div class="page-header" data-toggle="collapse"
					data-target="#${loop.index}">
					<h1>${entry.getKey() }</h1>
				</div>
				<div id="${loop.index}" class="collapse">
					<c:forEach items="${entry.getValue()}" var="course">
						<div class="row">
							<div class="rounded-div">
								<img src="${pageContext.request.contextPath}/cover/${course.id}"
									onerror="this.src = 'http://placehold.it/120x120'"
									class="img-responsive" />

								<h3>${course.title}</h3>
								
								<i class="glyphicon glyphicon glyphicon-star "></i>
								<c:set var="courseId" value="${course.id}" />
								<span>${averageratings[courseId]}</span>
								<div class="description">${course.description.substring(1,180)}...</div>

								<form action="<c:url value='/readmore' />" method="post"
									class="col-sm-6">
									<input type="hidden" name=courseId value="${course.id}">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<button class="btn btn-sm btn-lg btn-info" type="submit">
										<fmt:message key="read.more" />
									</button>
								</form>
								<sec:authorize access="hasRole('ROLE_USER')">
								<form id="ratingform" action="<c:url value = '/ratecourse'/>"
									method="post" class="col-sm-3">

										<input id="input-1" name="rating" value="${ratings[course.id]}"
										class="rating rating-loading" data-min="0" data-max="5"
										data-step="1" data-show-clear="false"
										data-show-caption="false" data-size="sm"/> 
										<c:out value="${ratings[course.id]}"/>
									
									
										<input
										type="hidden" name="courseId" value="${course.id}"/>
										 <input
										type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
										
										<c:out value ="${course.id}"/>
								</form>
								
								<div class="col-sm-1"
									style="padding-top: 10px; padding-left: 20px">
									<button class="btn btn-xs btn-info" type="submit"
										form="ratingform" style="margin: auto">
										<fmt:message key="rate.it" />
									</button>
								</div>
								</sec:authorize>
								
							</div>
						</div>

					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>