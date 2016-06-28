<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/registerlocation.css" rel="stylesheet"/>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<title>Location</title>
</head>
<body>
	<div class="container">
		<div class="row  pad-top">

			<div
				class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Where do you come from ? </strong>
					</div>
					<div class="panel-body">
						<form name='registerlocation' action="<c:url value='/registerlocation' />"
							method='POST' enctype="multipart/form-data">
							<input type="hidden" name="username" value="${username}"/>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<div class="form-group input-group" id="radiobox">
								<label class="radio"><input type="radio" name="locationId" value="1">North America</label>
								<label class="radio"><input type="radio" name="locationId" value="2">South America</label>
								<label class="radio"><input type="radio" name="locationId" value="3">Europe</label>
								<label class="radio"><input type="radio" name="locationId" value="4">Middle East</label>
								<label class="radio"><input type="radio" name="locationId" value="5">Far East</label>
							</div>
							
							<button class="btn btn-primary" type="submit">
								Continue
							</button>
						</form>
					
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>