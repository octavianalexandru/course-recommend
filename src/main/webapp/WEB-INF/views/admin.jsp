<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<html>
<head>
<title><fmt:message key="home" /></title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
	</div>
</body>
</html>
