<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="resources/css/user.css"/> 
<title><fmt:message key="home" /></title>

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
					<fmt:message key="top.rated" />
				</h1>
			</div>
		</div>
		
				
				<c:forEach var="course" items="${topRatedCourses}" varStatus="i">
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
	
</body>
</html>