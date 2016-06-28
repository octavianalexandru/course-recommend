<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/registerage.css" rel="stylesheet"/>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<title>Age</title>
</head>
<body>
	<div class="container">
		<div class="row  pad-top">

			<div
				class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> What is your age ? </strong>
					</div>
					<div class="panel-body">
						<form name='registerage' action="<c:url value='/registerage' />"
							method='POST' enctype="multipart/form-data" id="radiobox">
							<input type="hidden" name="username" value="${username}"/>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<label class="radio"><input type="radio" name="ageCategoryId" value="1">7-13</label>
							<label class="radio"><input type="radio" name="ageCategoryId" value="2">14-18</label>
							<label class="radio"><input type="radio" name="ageCategoryId" value="3">19-25</label>
							<label class="radio"><input type="radio" name="ageCategoryId" value="4">26-35</label>
							<label class="radio"><input type="radio" name="ageCategoryId" value="5">36-45</label>
							<label class="radio"><input type="radio" name="ageCategoryId" value="6">46-55</label>
							<label class="radio"><input type="radio" name="ageCategoryId" value="7">56+</label>
						
						<button class="btn btn-primary" type="submit">
								Complete Registration
						</button>
						
						</form>
					
						
					
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>