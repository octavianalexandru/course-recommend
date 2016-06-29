<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/jsinclude.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="add.course" /></title>
<link href="resources/css/addcourse.css" rel="stylesheet">
<link href="resources/css/datepicker.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../template/header.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<c:out value='${user.firstname} ${user.lastname}' />
						</h3>
					</div>
					<form id="addcourse" name='addcourse'
						action="<c:url value='/addcourse' />" method='POST'
						enctype="multipart/form-data">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="panel-body">
							<div class="row">
								<div class=" col-md-9 col-lg-9 ">
									<table class="table table-user-information addtable">
										<tbody>
											<tr>
												<td><fmt:message key="title" />:</td>
												<td><input type="text" name="title"
													class="form-control"
													placeholder="<fmt:message key="title" />" /></td>
											</tr>
											<tr>
												<td><fmt:message key="description" />:</td>
												<td><input type="text" name="description"
													class="form-control"
													placeholder="<fmt:message key="description" />" /></td>
											</tr>
											<tr>
												<td><fmt:message key="number.of.weeks" />:</td>
												<td><input type="text" name="numberOfWeeks"
													class="form-control"
													placeholder="<fmt:message key="number.of.weeks" />" /></td>
											</tr>
											<tr>
												<td><fmt:message key="start.date" />:</td>
												<td>
													<div class="controls">
														<div class="input-group">
															<input id="date-picker-1" type="text" name="startDate"
																value="<fmt:formatDate pattern="MM/dd/yyyy" 
            value="${fullCourse.startDate}" />"
																class="date-picker form-control" /> <label
																for="date-picker-1" class="input-group-addon btn"><i
																class="fa fa-calendar"></i> </label>
														</div>
												</td>
											</tr>
											<tr>
												<td><fmt:message key="end.date" />:</td>
												<td>
													<div class="controls">
														<div class="input-group">
															<input id="date-picker-2" type="text" name="endDate"
																value="<fmt:formatDate pattern="MM/dd/yyyy" 
            value="${fullCourse.endDate}" />"
																class="date-picker form-control" /> <label
																for="date-picker-2" class="input-group-addon btn"><i
																class="fa fa-calendar"></i> </label>
														</div>
												</td>
											</tr>
											<tr>
												<td><fmt:message key="category" />:</td>
												<td><select class="form-control" id="type" name="type">
														<c:forEach items="${allTypes}" var="type">
															<option value="${type.id }">${type.description }</option>
														</c:forEach>
												</select></td>
											</tr>
											<tr>
												<td><fmt:message key="cover" />:</td>
												<td><div class="form-group input-group">
														<span class="input-group-btn"> <span
															class=" btn btn-default btn-file"><fmt:message
																	key="cover" /><input type="file" name=photo id="data">
														</span>
														</span> <input type="text" id="valdfil" class="form-control"
															readonly />
													</div></td>
											</tr>
											
											<tr>
												<td>
													<h3>People from which location might better be interested in ?</h3>
													 
													 	<div class="checkbox">
 															 <label><input type="checkbox" name="locationid" value="1">North America</label>
														</div>
														<div class="checkbox">
  															<label><input type="checkbox" name="locationid" value="2">South America</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="locationid" value="3">Europe</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="locationid" value="4">Middle East</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="locationid" value="5">Far East</label>
														</div>
													
												</td>
												<td>
													<h3>People of what educational stage might find this course more useful ?</h3>
													
													 	<div class="checkbox">
 															 <label><input type="checkbox" name="educationalStageId" value="1">Primary School</label>
														</div>
														<div class="checkbox">
  															<label><input type="checkbox" name="educationalStageId" value="2">Secondary School</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="educationalStageId" value="3">Highschool</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="educationalStageId" value="4">University</label>
														</div>
													
												</td>
												<td>
													<h3>What age should be the people attending this course ?</h3>
													
													 	<div class="checkbox">
 															 <label><input type="checkbox" name="ageCategoryId" value="1">7-13</label>
														</div>
														<div class="checkbox">
  															<label><input type="checkbox" name="ageCategoryId" value="2">14-18</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="ageCategoryId" value="3">19-25</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="ageCategoryId" value="4">26-35</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="ageCategoryId" value="5">36-35</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="ageCategoryId" value="6">46-55</label>
														</div>
														<div class="checkbox">
 															 <label><input type="checkbox" name="ageCategoryId" value="7">56+</label>
														</div>
													
												</td>
											</tr>
										</tbody>
									</table>


								</div>
							</div>
						</div>
					</form>
					<div class="panel-footer">
						<a data-original-title="Broadcast Message" data-toggle="tooltip"
							type="button"
							href="javascript:document.getElementById('addcourse').submit()"
							class="btn btn-sm btn-primary"><i class="fa fa-floppy-o"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$(".date-picker").datepicker();

							$(".date-picker").on("change", function() {
								var id = $(this).attr("id");
								var val = $("label[for='" + id + "']").text();
								$("#msg").text(val + " changed");
							});
							$(document)
									.on(
											'change',
											'.btn-file :file',
											function() {
												var input = $(this), numFiles = input
														.get(0).files ? input
														.get(0).files.length
														: 1, label = input
														.val().replace(/\\/g,
																'/').replace(
																/.*\//, '');
												input.trigger('fileselect', [
														numFiles, label ]);
											});

							$('.btn-file :file').on('fileselect',
									function(event, numFiles, label) {
										console.log(numFiles);
										console.log(label);
										$("#valdfil").val(label);
									});
						});
	</script>
	<script src="resources/js/bootstrap-datepicker.js"></script>
</body>
</html>