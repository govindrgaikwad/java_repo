<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html ng-app>
<head>

<title>Contact</title>
<!-- Bootstrap core CSS -->

<!-- Jquery JS -->
<script type="text/javascript"
	src="<c:url value='/jquery/jquery-1.11.0.min.js' />"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/bootstrap/css/bootstrap.min.css' />"
	media="screen">

<!-- Bootstrap theme -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css' />"
	media="screen">

<!-- Bootstrap core JS -->
<script type="text/javascript"
	src="<c:url value='/bootstrap/js/bootstrap.min.js' />"></script>



<!-- Jquery JS -->
<script type="text/javascript"
	src="<c:url value='/angular/angular.min.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/js/ContactController.js' />"></script>

</head>

<body>
	<div class="panel panel-default" ng-controller="ContactController">
		<div class="panel-heading">Table Heading</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Type</th>
						<th>Length</th>
						<th>Abbrev</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="contact in contacts">
						<td>{{contact.contactID}}</td>
						<td>{{contact.addressLine1}}</td>
						<td>{{contact.addressLine2}}</td>
						<td>{{contact.city}}</td>

						<td>
							<div class="btn-group">
								<button type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-pencil"></span>
								</button>
								<button type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-trash"></span>
								</button>								
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="panel-footer">
			Panel footer <input type="submit" value="Submit" ng->
		</div>

	</div>
</body>


</html>