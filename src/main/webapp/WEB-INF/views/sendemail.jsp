<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="send.email" /></title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-sm-3">
					<p>Recipients</p>
					<ul class="list-group" id="recipientList">
						<c:forEach items="${students}" var="student">
							<li class="list-group-item student" id="${student.username }">${student.firstname }
								${student.lastname }</li>
						</c:forEach>
					</ul>
				</div>
				<div class="col-sm-9" style="padding-left: 20%;">

					
					<div class="col-lg-6">
						<form action="<c:url value='/sendemail' />" method="POST"
							id="myform">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<input type="hidden" name="emails" value="">	
							<div class="form-group">
								<label for="InputEmail">Subject</label>
								<div class="input-group">
									<input type="text" class="form-control" id="emailSubject"
										name="emailSubject"> <span class="input-group-addon"><i
										class="fa fa-envelope-o bigicon"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="InputMessage">Message</label>
								<div class="input-group">
									<textarea name="emailMessage" id="emailMessage"
										class="form-control" rows="5"></textarea>
									<span class="input-group-addon"><i
										class="fa fa-pencil-square-o bigicon"></i></span>
								</div>
							</div>
							<input type="submit" name="submit" id="submit" value="Send Email"
							class="btn btn-info pull-right">
						</form>
						
					</div>

				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).on('click', 'ul .student', function(e) {
			var $this = $(this);
			if (!$this.hasClass('active')) {
				$this.addClass('active');
			} else {
				$this.removeClass('active');
			}
			var recipientList = "";
			$('#recipientList li.active').each(function(index) {
				recipientList = recipientList + $(this).attr('id') + ",";
			});
			recipientList = recipientList.slice(0, -1);
			$('input[name="emails"]').attr('value', recipientList);
			e.preventDefault();
		});
	</script>
</body>
</html>