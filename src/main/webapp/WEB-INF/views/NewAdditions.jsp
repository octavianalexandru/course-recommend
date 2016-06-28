<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><fmt:message key="newadditions" /></title>
<link href="resources/css/newadditions.css" rel="stylesheet"/>
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
		<jsp:include page="template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
	</div>
	
	<div class="container" style="padding-left:15%">
		<div class="row" style="padding-bottom:3%">
			<div class="col-md-12">
				<h1>
					<fmt:message key="newadditions" />
				</h1>
			</div>
		</div>
		
		<div id="page-wrapper">
			<c:forEach items="${newAddedCoursesWithDates}" var="mapEntry" varStatus="j">
				<div class="row">
					<h2><c:out value ="${mapEntry.key}"/></h2>
				</div>
				
				<c:set value="${mapEntry.value}" var="mapValue" />
				
				<c:forEach items="${mapValue}" var="course" varStatus="i">
					
					<c:set var="courseId" value="${course.id}"/>
  					 
  					 <c:if test="${i.index%3==0}">	
  					 		<div class="row">
  					 </c:if>
  					 
					<div class="col-sm-4">
						<div class="tile" id ="box">
							<img src="${pageContext.request.contextPath}/cover/${course.id}"
									onerror="this.src = 'http://placehold.it/120x120'"
									class="img-responsive" />
								<h3>${course.title}</h3>
								<form action="<c:url value='/readmore' />" method="post"
									class="col-sm-6">
									<input type="hidden" name="courseId" value="${course.id}">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<button class="btn btn-sm btn-lg btn-info" type="submit">
										<fmt:message key="read.more" />
									</button>
								</form>
								
						</div>
						
					</div>
					
					<c:if test="${i.index%3==2}">
						</div>
					</c:if>	
					
				</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
	
</body>
</html>