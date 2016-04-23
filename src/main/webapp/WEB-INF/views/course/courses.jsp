<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="courses" /></title>
	<link href="resources/css/cousrses.css" rel="stylesheet" />
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
								<div class="description">${course.description.substring(1,180)}...</div>
								<form action="<c:url value='/readmore' />" method="post">
									<input type="hidden" name="courseId" value="${course.id}">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<button class="btn btn-sm btn-lg btn-info" type="submit">
										<fmt:message key="read.more" />
									</button>
									<span class="text-muted pull-right">Number of weeks :
										${course.numberOfWeeks}</span>

								</form>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>