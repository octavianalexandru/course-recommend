<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/registereducationalstage.css" rel="stylesheet"/>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<title>Educational Stage</title>
</head>
<body>
	<div class="container">
		<div class="row  pad-top">

			<div
				class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> What is your educational stage ? </strong>
					</div>
					<div class="panel-body">
						<form name='registereducationalstage' action="<c:url value='/registereducationalstage' />"
							method='POST' enctype="multipart/form-data">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input type="hidden" name="username" value="${username}"/>
							<div class="form-group input-group" id="radiobox">
								<label class="radio"><input type="radio" name="educationalStageId" value="1">Primary School</label>
								<label class="radio"><input type="radio" name="educationalStageId" value="2">Secondary School</label>
								<label class="radio"><input type="radio" name="educationalStageId" value="3">Highschool</label>
								<label class="radio"><input type="radio" name="educationalStageId" value="4">University</label>
								
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