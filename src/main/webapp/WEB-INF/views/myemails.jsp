<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="my.emails" /></title>
<link href="resources/css/myemails.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<jsp:include page="template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
		<div id="page-wrapper">
			<c:forEach items="${allEmails}" var="currentEmail">
				<div class="rounded-div">
					<img
						src="${pageContext.request.contextPath}/photo/${currentEmail.teacher}"
						onerror="this.src = 'http://placehold.it/120x120'"
						class="img-responsive" />
					<h3>${currentEmail.subject}</h3>
					<div class="description">${currentEmail.message}</div>
					<span>${currentEmail.firstName } ${currentEmail.lastName}</span> <span
						class="pull-right"><i class="fa fa-clock-o"></i> <fmt:formatDate
							pattern="MM/dd/yyyy" value="${currentEmail.datesent}" /></span>

				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>