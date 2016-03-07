<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="login" /></title>
<link href="resources/css/login.css" rel="stylesheet">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<body onload='document.login.username.focus();'>
	<div class="container">
		<div class="card card-container">
			<img id="profile-img" class="profile-img-card"
				src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
			<p id="profile-name" class="profile-name-card"></p>
			<c:if test="${not empty error}">
				<div>${error}</div>
			</c:if>
			<c:if test="${not empty message}">
				<div>${message}</div>
			</c:if>
			<form name='login' action="<c:url value='/login' />" method='POST'>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <span id="reauth-email"
					class="reauth-email"> </span> <input type="text" name="username"
					class="form-control" placeholder="<fmt:message key="user.name"/>"
					required autofocus> <input type="password" name="password"
					class="form-control" placeholder=<fmt:message key="password"/>
					required>

				<div id="remember" class="checkbox"></div>
				<button class="btn btn-lg btn-primary btn-block btn-signin"
					type="submit">
					<fmt:message key="sign.in" />
				</button>
			</form>
			<!-- /form -->
			<a href="${pageContext.request.contextPath}/register"
				class="forgot-password"> <fmt:message key="register" />
			</a>
		</div>
		<!-- /card-container -->
	</div>
	<!-- /container -->
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</body>

</html>