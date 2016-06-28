<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="my.courses" /></title>
<link href="resources/css/courses.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
		<div id="page-wrapper">
			<c:if test="${empty courses}">
				<h1><c:out value="There are no courses submitted by you yet"/></h1> 
			</c:if>
			<c:forEach items="${courses}" var="course">
				<div class="row">
					<div class="rounded-div">
						<img src="${pageContext.request.contextPath}/cover/${course.id}"
							onerror="this.src = 'http://placehold.it/120x120'"
							class="img-responsive" />
						<h3>${course.title}</h3>
						<div class="description">${course.description}...</div>
						<form action="<c:url value='/readmore' />" method="post" class="myform">
							<input type="hidden" name="courseId" value="${course.id}">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<button class="btn btn-sm btn-lg btn-info" type="submit">
								<fmt:message key="read.more" />
							</button>
						
						</form>
						<form action="<c:url value='/editcourse' />" method="post">
							<input type="hidden" name="courseId" value="${course.id}">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<button class="btn btn-sm btn-lg btn-info" type="submit">
								Edit Course</button>
							<span class="text-muted pull-right">Number of weeks :
								${course.numberOfWeeks}</span>
						</form>
					</div>
				</div>
			</c:forEach>


		</div>
	</div>
</body>
</html>